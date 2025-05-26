package io.toy.roomy.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class FileUploadUtil {

    private static final String projectPath = "D:/jb/study/roomy/src/main";
    //private static final String projectPath = "C:/myIntellij/roomy/src/main";

    public static String saveFile(MultipartFile file, String filePath) throws IOException {
        filePath = projectPath + filePath;

        // 확장자 추출
        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileExtension = getFileExtension(originalFilename);

        // 유니크한 파일명 생성
        String savedFileName = UUID.randomUUID() + "." + fileExtension;

        // 저장 경로 생성
        File saveDirectory = new File(filePath);
        if (!saveDirectory.exists()) {
            boolean created = saveDirectory.mkdirs();
            if (!created) {
                throw new IOException("업로드 폴더 생성에 실패했습니다: " + filePath);
            }
        }

        // 실제 파일 저장
        File savedFile = new File(saveDirectory, savedFileName);
        file.transferTo(savedFile);

        return savedFileName; // 또는 전체 경로 return도 가능
    }

    public static boolean deleteFile(String filePath) {
        filePath = projectPath + filePath;
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

    private static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
