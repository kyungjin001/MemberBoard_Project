# SpringBoot_MemberProject
    
## Member_Project

### Controller
    1. MainController
        - index를 띄워주는 역할을 함
    
    2. MemberController
        - 회원등록(/member/save),로그인(/member/login),
        회원수정(/member/update),마이페이지(/member/mypage/{memberId}),
        회원목록(/member/),회원상세조회(/member/{memberId})
        메서드를 관리해줌

### HTML
    3. save.html
        - form태그와 input태그를 활용하여 양식작성
        - 이메일 중복체크 ajax 사용(동일 이메일로 등록불가)
    
    4. findAll.html
        - table을 사용하여 전체회원목록 출력
        - 타임리프 사용
        - 타임리프를 활용한 사진출력 
        - ajax,get방식을 활용하여 상세조회 및 삭제 가능

    
    5. myPage.html
        - 회원수정 가능
        - 타임리프 활용
        - 회원 본인은 수정 및 삭제 가능
        - 관리자는 삭제만 가능하도록 설정(th:if 사용)

    6. login.html
        - form,input 태그를 활용하여 이메일, 비밀번호만 받도록 설정
        - 보내는 방식은 post 방식

    7. findById(detail).html
        - table 태그를 활용하여 회원정보 출력
        - 타임리프 사용

    8. update.html
        - 타임리프 적극활용
        - put,post 두가지 방식 적극활용
        - ajax를 활용해서 비밀번호가 일치해야 수정가능하도록 설정
          (주의할 점 : sumit으로 하면 무조건 전송이므로 꼭 타입은 버튼으로 할 것)  

### 특이사항
    1. interceptor 기능 처음 활용
        - 직역하면 중간에 가로채는 기능
        - 로그인/비로그인 확인페이지를 설정가능
        - logincheckInterceptor,webConfig,로그인세션 적극활용


## Board_Project

### controller
    1. boardController
    - 글등록(/board/save), 글수정(/board/update),목록페이징(/board),
    글목록(/member/),글상세조회(/board/{boardId}),검색(board/search)
    메서드를 관리해줌

### HTML
    2. save.html
        - 타임리프를 활용하여 작성자 = 회원이메일 세션저장(th:value)
        - form/input 태그 활용

    3. findAll.html
        - 타임리프 기능 활용
        - table 태그 활용
        - ajax를 활용한 상세조회, 삭제 확인가능

    4. update.html
        - 타임리프 적극활용
        - put,post 두가지 방식 적극활용

    5. pagin.html
        - 기존 프레임워크와 유사
        - 해당 페이지에서 검색기능 활용
        - 타임리프 적극활용

    6. search.html
        - 검색기능을 출력해주는 페이지
        - Model값과 table 태그를 활용하여 단순 출력페이지


    
## 추가 개선해야할 사항
    - validation check, global error 등의 타임리프를 좀 더 연습해볼 것
    - 테스트코드에 대한 도전을 계속해볼 것
    - restful 주소에 대한 더 높은 이해도 필요
