# Assignment1

학번 : 20181686\
이름 : 장병준

1. 첫번째 화면 (Relative Layout 사용)
    - 앱 접속 페이지, 회원 ID/비밀번호(EditView), 로그인/회원가입(Button)
    - 첫번째 화면 초기화시에 파일에서 개인정보 읽어 오기
    - ID, 비밀번호 입력 시 기존에 가입한 회원 ID, 비밀번호 체크 오류 시 에러 메시지 출력
    - ID, 비밀번호 입력이 정상이고 로그인 버튼 클릭 시 세번째 페이지 이동 
2. 두번째 화면 (Linear Layout 사용)
    - 회원가입 페이지, 첫번째 페이지에서 회원가입 버튼 클릭 시 출력
    - ID(EditView, 중복검사), 비밀번호(EditView, 자릿수/특수키 등 규칙 체크)
    - 이름/전화번호/주소(EditView)
    - 개인정보 사용 동의 간략 약관(TextView), 동의 여부(Radio Button, Decline/Accept)
    - 회원정보는 파일로 저장하고 첫번째 페이지로 이동
3. 세번째 화면 (Constraint Layout, Table Layout, Grid Layout, Frame Layout 중 하나 사용)
    - 첫번째 페이지에서 ID, 비밀번호 입력 시 정상이고 로그인 버튼 클릭 시 화면 출력
    - 세번째 화면을 간단한 기능을 수행하도록 구성 (ex. 간편 계산기 등)
    - View을 상속한 여러가지 위젯을 사용하여 화면을 구성(기능에 맞는 위젯 선택하여 구성)
        - View Group을 상속한 위젯 ListView, GridView, AdapterView, ToolBar 등
        - Text View을 상속한 CheckBox, Switch, ToggleButton, RadioButton 등
        - ImageView, ImageButton 등