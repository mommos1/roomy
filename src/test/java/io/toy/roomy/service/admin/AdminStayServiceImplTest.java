package io.toy.roomy.service.admin;

import io.toy.roomy.common.FileUploadUtil;
import io.toy.roomy.domain.Stay;
import io.toy.roomy.domain.type.StayType;
import io.toy.roomy.dto.request.stay.StayRequest;
import io.toy.roomy.dto.request.stay.StayUpdateRequest;
import io.toy.roomy.dto.response.stay.StayDetailResponse;
import io.toy.roomy.dto.response.stay.StayListResponse;
import io.toy.roomy.repository.StayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Mockito 확장 기능 사용
class AdminStayServiceImplTest {

    @Mock // Mock 객체 생성
    private StayRepository stayRepository;

    @InjectMocks // @Mock으로 생성된 객체를 주입받는 실제 테스트 대상 객체
    private AdminStayServiceImpl adminStayService;

    private StayRequest stayRequest;
    private MultipartFile mockImageFile;
    private Stay stayFixture;
    private final String UPLOAD_DIR = "/images/stayRegImage";


    @BeforeEach
    void setUp() {
        // 테스트에 사용될 공통 fixture 데이터 설정
        mockImageFile = new MockMultipartFile(
                "imageFile", // form 필드 이름
                "test-image.jpg", // 파일 이름
                "image/jpeg", // 컨텐츠 타입
                "test image content".getBytes() // 파일 내용
        );

        stayRequest = StayRequest.builder()
                .name("테스트 호텔")
                .location("테스트 위치")
                .type(StayType.HOTEL)
                .build();

        stayFixture = Stay.builder()
                .id(1L)
                .name("기존 호텔")
                .location("기존 위치")
                .type(StayType.HOTEL)
                .filePath(UPLOAD_DIR + "/existing-image.jpg")
                .orgFileName("existing-image.jpg")
                .regDt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("숙소 등록 성공 - 이미지 포함")
    void regStay_withImage_shouldSaveStay() throws IOException {
        // given
        String savedFileName = "mock-saved-image.jpg";

        // FileUploadUtil.saveFile 정적 메서드 Mocking
        // try-with-resources 구문을 사용하여 MockedStatic 객체가 자동으로 close 되도록 함
        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            mockedFileUploadUtil.when(() -> FileUploadUtil.saveFile(any(MultipartFile.class), anyString()))
                    .thenReturn(savedFileName); // saveFile 메서드가 호출되면 savedFileName을 반환하도록 설정

            // when
            adminStayService.regStay(stayRequest, mockImageFile);

            // then
            // stayRepository.save가 호출되었는지, 어떤 인자로 호출되었는지 검증
            ArgumentCaptor<Stay> stayArgumentCaptor = ArgumentCaptor.forClass(Stay.class);
            then(stayRepository).should(times(1)).save(stayArgumentCaptor.capture());

            Stay savedStay = stayArgumentCaptor.getValue();
            assertThat(savedStay.getName()).isEqualTo(stayRequest.getName());
            assertThat(savedStay.getLocation()).isEqualTo(stayRequest.getLocation());
            assertThat(savedStay.getType()).isEqualTo(stayRequest.getType());
            assertThat(savedStay.getOrgFileName()).isEqualTo(mockImageFile.getOriginalFilename());
            assertThat(savedStay.getFilePath()).isEqualTo(UPLOAD_DIR + "/" + savedFileName);

            // FileUploadUtil.saveFile이 올바른 인자들로 호출되었는지 검증
            mockedFileUploadUtil.verify(() -> FileUploadUtil.saveFile(mockImageFile, UPLOAD_DIR), times(1));
        }
    }

    @Test
    @DisplayName("숙소 등록 시 IOException 발생")
    void regStay_whenIOException_shouldThrowException() throws IOException {
        // given
        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            mockedFileUploadUtil.when(() -> FileUploadUtil.saveFile(any(MultipartFile.class), anyString()))
                    .thenThrow(new IOException("파일 저장 실패"));

            // when & then
            assertThrows(IOException.class, () -> {
                adminStayService.regStay(stayRequest, mockImageFile);
            }, "파일 저장 중 IOException이 발생해야 합니다.");

            then(stayRepository).should(never()).save(any(Stay.class)); // IOException 발생 시 save는 호출되지 않아야 함
        }
    }


    @Test
    @DisplayName("숙소 목록 조회 - 결과 있음")
    void getAll_shouldReturnStayList() {
        // given
        List<Stay> stays = List.of(stayFixture,
                Stay.builder()
                        .id(2L)
                        .name("모텔 테스트")
                        .location("모텔 위치")
                        .type(StayType.MOTEL)
                        .build());
        given(stayRepository.findAll()).willReturn(stays);

        // when
        List<StayListResponse> result = adminStayService.getAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(stayFixture.getName());
        assertThat(result.get(1).getName()).isEqualTo("모텔 테스트");
        then(stayRepository).should(times(1)).findAll();
    }

    @Test
    @DisplayName("숙소 목록 조회 - 결과 없음")
    void getAll_whenNoStays_shouldReturnEmptyList() {
        // given
        given(stayRepository.findAll()).willReturn(List.of());

        // when
        List<StayListResponse> result = adminStayService.getAll();

        // then
        assertThat(result).isEmpty();
        then(stayRepository).should(times(1)).findAll();
    }

    @Test
    @DisplayName("숙소 상세 정보 조회 - 성공")
    void getStayDetail_whenStayExists_shouldReturnStayDetail() {
        // given
        given(stayRepository.findById(stayFixture.getId())).willReturn(Optional.of(stayFixture));

        // when
        StayDetailResponse result = adminStayService.getStayDetail(stayFixture.getId());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(stayFixture.getName());
        assertThat(result.getLocation()).isEqualTo(stayFixture.getLocation());
        then(stayRepository).should(times(1)).findById(stayFixture.getId());
    }

    @Test
    @DisplayName("숙소 상세 정보 조회 - 해당 숙소 없음")
    void getStayDetail_whenStayNotExists_shouldThrowException() {
        // given
        Long nonExistentStayId = 999L;
        given(stayRepository.findById(nonExistentStayId)).willReturn(Optional.empty());

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            adminStayService.getStayDetail(nonExistentStayId);
        });
        assertThat(exception.getMessage()).isEqualTo("해당 숙소가 존재하지 않습니다.");
        then(stayRepository).should(times(1)).findById(nonExistentStayId);
    }

    @Test
    @DisplayName("숙소 정보 수정 - 새 이미지로 변경")
    void updateStay_withNewImage_shouldUpdateStayAndFiles() throws IOException {
        // given
        StayUpdateRequest updateRequest = StayUpdateRequest.builder()
                .id(stayFixture.getId())
                .name("수정된 호텔 이름")
                .location("수정된 위치")
                .type(StayType.PENSION)
                .orgFilePath(stayFixture.getFilePath()) // 기존 파일 경로
                .build();

        String newSavedFileName = "new-mock-saved-image.jpg";

        given(stayRepository.findById(stayFixture.getId())).willReturn(Optional.of(stayFixture));

        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            // 기존 파일 삭제 mocking (void 메서드)
            mockedFileUploadUtil.when(() -> FileUploadUtil.deleteFile(updateRequest.getOrgFilePath()))
                    .then(invocation -> null); // void 메서드는 thenAnswer나 then(invocation -> null) 사용
            // 새 파일 저장 mocking
            mockedFileUploadUtil.when(() -> FileUploadUtil.saveFile(mockImageFile, UPLOAD_DIR))
                    .thenReturn(newSavedFileName);

            // when
            adminStayService.updateStay(updateRequest, mockImageFile);

            // then
            // FileUploadUtil.deleteFile 호출 검증
            mockedFileUploadUtil.verify(() -> FileUploadUtil.deleteFile(updateRequest.getOrgFilePath()), times(1));
            // FileUploadUtil.saveFile 호출 검증
            mockedFileUploadUtil.verify(() -> FileUploadUtil.saveFile(mockImageFile, UPLOAD_DIR), times(1));

            // DTO에 새로운 파일 경로와 이름이 설정되었는지 확인 (updateRequest 객체는 서비스 내부에서 변경됨)
            assertThat(updateRequest.getFilePath()).isEqualTo(UPLOAD_DIR + "/" + newSavedFileName);
            assertThat(updateRequest.getOrgFileName()).isEqualTo(mockImageFile.getOriginalFilename());

            // Stay 엔티티가 올바르게 업데이트 되었는지 확인
            assertThat(stayFixture.getName()).isEqualTo(updateRequest.getName());
            assertThat(stayFixture.getLocation()).isEqualTo(updateRequest.getLocation());
            assertThat(stayFixture.getType()).isEqualTo(updateRequest.getType());
            assertThat(stayFixture.getFilePath()).isEqualTo(updateRequest.getFilePath()); // stay.update 내부에서 dto의 값을 사용하므로
            assertThat(stayFixture.getOrgFileName()).isEqualTo(updateRequest.getOrgFileName());
        }
    }

    @Test
    @DisplayName("숙소 정보 수정 - 이미지 변경 없음 (기존 이미지 유지)")
    void updateStay_withoutNewImage_shouldUpdateStayOnly() throws IOException {
        // given
        StayUpdateRequest updateRequest = StayUpdateRequest.builder()
                .id(stayFixture.getId())
                .name("수정된 호텔 이름 (이미지 변경 없음)")
                .location("수정된 위치 (이미지 변경 없음)")
                .type(StayType.MOTEL)
                .filePath(stayFixture.getFilePath()) // 기존 파일 경로 유지 (DTO에 이미 설정되어 있다고 가정)
                .orgFileName(stayFixture.getOrgFileName()) // 기존 파일명 유지
                .build();

        given(stayRepository.findById(stayFixture.getId())).willReturn(Optional.of(stayFixture));
        MultipartFile nullImageFile = null; // 또는 new MockMultipartFile("", new byte[0]) 등 isEmpty()가 true인 경우

        // when
        adminStayService.updateStay(updateRequest, nullImageFile); // 이미지를 null로 전달

        // then
        // FileUploadUtil 관련 메서드는 호출되지 않아야 함
        // FileUploadUtil.deleteFile 이나 saveFile이 호출되지 않았는지 확인하기 위해 MockedStatic을 사용할 수도 있으나,
        // 이 경우 null을 전달했으므로 서비스 로직에서 if (image != null && !image.isEmpty()) 에 걸려 실행되지 않음을 기대
        // (만약 FileUploadUtil이 항상 mocking 되어야 한다면 try-with-resources로 감싸야 함)

        assertThat(stayFixture.getName()).isEqualTo(updateRequest.getName());
        assertThat(stayFixture.getLocation()).isEqualTo(updateRequest.getLocation());
        assertThat(stayFixture.getType()).isEqualTo(updateRequest.getType());
        // 이미지 관련 필드는 변경되지 않아야 함 (updateRequest에 이미 기존 값이 들어있다고 가정)
        assertThat(stayFixture.getFilePath()).isEqualTo(updateRequest.getFilePath());
        assertThat(stayFixture.getOrgFileName()).isEqualTo(updateRequest.getOrgFileName());

        // 명시적으로 파일 유틸리티가 호출되지 않았음을 확인하고 싶다면
        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            mockedFileUploadUtil.verifyNoInteractions(); // FileUploadUtil의 어떤 메서드도 호출되지 않았음을 검증
        }
    }


    @Test
    @DisplayName("숙소 정보 수정 - 해당 숙소 없음")
    void updateStay_whenStayNotExists_shouldThrowException() {
        // given
        Long nonExistentStayId = 999L;
        StayUpdateRequest updateRequest = StayUpdateRequest.builder().id(nonExistentStayId).name("존재하지 않는 호텔").build();
        given(stayRepository.findById(nonExistentStayId)).willReturn(Optional.empty());

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            adminStayService.updateStay(updateRequest, mockImageFile);
        });
        assertThat(exception.getMessage()).isEqualTo("해당 숙소가 존재하지 않습니다.");
        then(stayRepository).should(times(1)).findById(nonExistentStayId);
    }


    @Test
    @DisplayName("숙소 삭제 - 성공")
    void deleteStay_whenStayExists_shouldDeleteStayAndFile() {
        // given
        given(stayRepository.findById(stayFixture.getId())).willReturn(Optional.of(stayFixture));

        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            mockedFileUploadUtil.when(() -> FileUploadUtil.deleteFile(stayFixture.getFilePath()))
                    .then(invocation -> null); // void 메서드 mocking

            // when
            assertDoesNotThrow(() -> adminStayService.deleteStay(stayFixture.getId()));

            // then
            mockedFileUploadUtil.verify(() -> FileUploadUtil.deleteFile(stayFixture.getFilePath()), times(1));
            then(stayRepository).should(times(1)).delete(stayFixture);
        }
    }

    @Test
    @DisplayName("숙소 삭제 - 해당 숙소 없음")
    void deleteStay_whenStayNotExists_shouldThrowException() {
        // given
        Long nonExistentStayId = 999L;
        given(stayRepository.findById(nonExistentStayId)).willReturn(Optional.empty());

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            adminStayService.deleteStay(nonExistentStayId);
        });
        assertThat(exception.getMessage()).isEqualTo("해당 숙소가 존재하지 않습니다.");

        // FileUploadUtil.deleteFile은 호출되지 않아야 함
        try (MockedStatic<FileUploadUtil> mockedFileUploadUtil = mockStatic(FileUploadUtil.class)) {
            mockedFileUploadUtil.verifyNoInteractions();
        }
        then(stayRepository).should(never()).delete(any(Stay.class));
    }
}