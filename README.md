# 📌 Roomy - 공간 예약 플랫폼

> 강의실과 독서실을 통합하여 예약할 수 있는 실사용 기반 공간 예약 플랫폼입니다.  
> 네카라쿠배, 당근, 오늘의집 등 서비스 회사 취업을 목표로 **Spring Boot 백엔드 중심 구조**로 설계되었습니다.

---

## 🛠️ Tech Stack

| 구분 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.x, Spring Security, Spring Data JPA |
| Build Tool | Gradle |
| View | Thymeleaf |
| DB | MySQL (or H2 for local test) |
| Version Control | Git + GitHub |
| CI/CD | GitHub Actions (예정) |

---

## 🧩 주요 기능

### 👤 사용자 (USER)
- 회원가입, 로그인
- 공간(강의실/독서실) 목록 및 상세조회
- 공간 예약 등록/조회/취소
- 내 예약 내역 확인

### 👩‍💼 관리자 (ADMIN)
- 공간 등록/수정/삭제
- 전체 예약 내역 조회
- 예약 통계(선택 구현)

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

- RoomType: [CLASSROOM, STUDY_ROOM]
- Role: [USER, ADMIN]
```

---

## 📌 향후 개발 계획
- ✅ 예약 중복 방지 로직
- ✅ 사용자 권한 분리(Spring Security)
- ⏳ 관리자 통계 기능
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

## 🙋‍♂️ 개발자

| 이름 | 역할 |
|------|------|
| 허준범 | 백엔드, 기획, 설계, 테스트, 문서화 (Full-stack 개인 개발) |
