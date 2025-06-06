# 📌 Roomy - 숙소 예약 플랫폼

> 호텔, 모텔, 펜션을 통합하여 예약할 수 있는 실사용 기반 숙소 예약 플랫폼입니다.  
> **Spring Boot 백엔드 중심 구조**로 설계되었습니다.  
> 1인으로 진행한 개인 프로젝트입니다.

## 🛠️ Tech Stack

| 구분 | 기술 |
|------|------|
| Language | Java 24 |
| Framework | Spring Boot 3.4.4, Spring Security, Spring Data JPA |
| Build Tool | Gradle |
| View | Thymeleaf |
| DB | MySQL (or H2 for local test) |
| Version Control | Git + GitHub |
| CI/CD | GitHub Actions (예정) |

## 🧩 주요 기능

**(작성된 블로그 글이 있는 내용은 링크를 걸어두었습니다.)**

## 👤 사용자 (USER)

### 회원가입
- ✅ 아이디, 비밀번호, 닉네임 입력받아 회원가입.
- ✅ [비밀번호는 BCrypt로 암호화되어 저장.](https://mommos1.tistory.com/101)
- ✅ 아이디 중복 불가능.

### 로그인
- ✅ 회원가입 시 입력한 아이디, 비밀번호를 입력해 로그인.
- ✅ 입력된 비밀번호는 암호화되어 검증.
- ✅ [로그인 완료 시 JWT토큰 발급 후 서버에서 검증.](https://mommos1.tistory.com/102)

### 숙소(호텔/모텔/펜션) 목록 및 상세조회
- ✅ 예약 가능한 숙소 목록 조회.
- ✅ 숙소 대표 이미지, 숙소이름, 장소, 리뷰 조회.
- ⏳ 날짜, 인원선택에 따라서 예약 가능한 숙소 조회.

### 숙소 예약
- ✅ 선택한 객실 예약 진행.
- ✅ 선택된 날짜, 인원에 예약이 가능한 객실인지 다시 검증.
- ⏳ 동시성 문제 처리.
- ⏳ 예약이 모두 완료된 객실은 예약 불가 처리.

### 내 예약 내역 확인
- ⏳ 예약한 객실 조회 및 예약 취소.

## 👩‍💼 관리자 (ADMIN)

### 관리자 페이지
- ✅ 관리자 계정으로 로그인 시 접속 가능한 페이지.
- ⏳ 관리자 페이지는 매 요청마다 토큰 검증.
- ⏳ 메인 대시보드로 총 숙소 수, 당일 예약 수, 총 회원 수 확인 가능함.

### 숙소 등록/수정/삭제 (숙소 관리)
- ✅ 등록된 숙소 목록 리스트로 출력. 
- ✅ 등록하기 버튼으로 등록 가능.
- ✅ 대표이미지, 숙소이름, 위치, 숙소 타입(모텔, 호텔, 펜션) 입력 후 등록.
- ⏳ 수정 버튼으로 수정페이지 진입 및 변경사항 적용.
- ✅ 삭제 버튼으로 숙소 삭제.

### 객실 등록/수정/삭제 (숙소 관리)
- ✅ 등록된 객실 목록 리스트로 출력.
- ✅ 등록하기 버튼으로 등록 가능.
- ✅ 객실이름, 금액, 인원 수, 설명 입력 후 등록.
- ✅ 수정 버튼으로 수정페이지 진입 및 변경사항 적용.
- ✅ 삭제 버튼으로 숙소 삭제.

### 전체 예약 내역 조회 (예약 관리)
- ⏳ 등록된 숙소 리스트 출력 및 상세조회 버튼.
- ⏳ 상세 조회 시 상단의 셀렉트박스로 날짜를 선택하여 예약자 명단 조회.
- ⏳ 예약자 아이디, 닉네임, 예약 등록 시간 조회.

### 예약 통계
- ⏳ 준비중.

---

## 🗂️ 패키지 구조 (io.toy.roomy)

```
└── roomy
    ├── controller        # 웹 요청 처리
    ├── domain            # JPA Entity 및 enum
    ├── dto               # 요청/응답 DTO
    ├── repository        # JPA 인터페이스
    ├── service           # 비즈니스 로직
    └── config            # Spring Security 등 설정
```

---

## 📄 ERD (간단 요약)

```
User (1) ──── (N) Reservation (N) ──── (1) Room

- StayType: [HOTEL, MOTEL, PENSION]
- MemberType: [USER, ADMIN]
```

---

## 📌 향후 개발 계획
- ⏳ 예약 중복 방지 로직
- ⏳ 사용자 권한 분리(Spring Security)
- ⏳ GitHub Actions로 CI/CD 구축
- ⏳ Docker + EC2 배포

---

## 📎 커밋 컨벤션 (예정)

```bash
feat: 기능 추가
fix: 버그 수정
docs: 문서 변경 (README 등)
style: 코드 스타일 (세미콜론 등)
refactor: 리팩토링
test: 테스트 코드 추가
chore: 빌드/패키지 설정
```

---
