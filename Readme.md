# Assignment1

- 학번 : 20181686
- 이름 : 장병준

## 테스트 환경

- Android Studio 3.5
- Build #AI-191.8026.42.35.5791312, built on August 9, 2019
- JRE: 1.8.0_202-release-1483-b49-5587405 x86_64
- JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
- KOTLIN: 1.3.50-release-Studio3.5-1
- macOS 10.15

## 화면 구성(과제)

1. 첫번째 화면 (Relative Layout 사용)
    - 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
    - ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
    - ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동 
2. 두번째 화면 (Linear Layout 사용)
    - 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
    - ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
    - 이름/전화번호/주소(EditView)
    - 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
    - 회원정보는 DB로 저장하고 첫번째 페이지로 이동
3. 세번째 화면 (Grid Layout 사용)
    - 첫번째 페이지에서 ID, 비밀번호 입력 시 정상이고 로그인 버튼 클릭 시 화면 출력
    - 비밀번호 변경 페이지 및  회원 정보 페이지