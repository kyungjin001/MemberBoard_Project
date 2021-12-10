# MemberBoard
## package
### com.icia.board.controller
    
    
    - HomeController
        -  기본주소요청을 처리하는 메서드를 만들고 관리해줌
        - return 값은 index로 보내준다.
   
   
    -  MemberController
        - member 폴더안에 있는 모든 jsp 관리 및 메서드 생성 역할
        - 처음에 어노테이션 생성 @Controller
        - @Autowired 이용하여 MemberService 호출
           
            1. 회원가입 링크(GET)를 눌렀을 때 처리해주는 insertform 메서드 생성, 리턴값은 insert(회원가입페이지)로 보내주기
            
            2. insert(회원가입페이지)를 처리해주는 메서드 생성
                - insert에서 post로 보냈기 때문에 메서드는 post 방식
                - 매개변수는 @ModelAttribute(전체 호출) MemberDTO(회원정보) members(객체이름), throws IllegalStateException, IOException (사진 예외처리)
                - int save = ms.insert(members); 우변(객체)을 담아줄 변수(save)를 만들어 주고 if문을 통해 0보다 크면 인덱스로 보내주고 작으면 인서트 페이지 유지
            
            3. 로그인, 로그아웃 메서드 처리 메서드 생성
                - login에서 post로 보냈기 때문에 메서드는 post 방식
                 - 매개변수는 @ModelAttribute(전체 호출) MemberDTO(회원정보) members(객체이름)
                 - String resultPage = ms.login(members) 우변(객체)을 담아줄 변수(resultPage)를 만들어 주고 리턴 resultPage로 보내주기
                 - 로그아웃 매개변수는 HttpSession session(세션값-아이디를 지우기 위해)
                 - session.invalidate() = 세션에 저장된 데이터를 지움.
                 - 로그아웃되면 인덱스로 보내기
            
            4. 회원목록 처리
                - 매개변수는 model값
                - 전체목록 출력해야하니 DTO를 리스트에 담아주고 ms.findAll() 넘겨주기
                - model.addAttribute("mList", mList) -> 화면출력 기능
                - 리턴은 findAll 페이지
            
            5. 상세조회 처리
                - 메서드명 : findById
                - 매개변수 : @RequestParam으로 m_number만 요청하고 모델값 요청
                - MemberDTO members = ms.findById(m_number) 객체에 해당 메서드 담기
                - model.addAttribute("members", members) -> 화면출력 용도!
                - 리턴값은 detail 페이지로
            
            6. 삭제 처리
                - 매개변수 : @RequestParam으로 m_number만 요청하고 모델값 요청
                - ms.delete(m_number) -> 서비스로 보내주기
                - 회원탈퇴되면 인덱스로 가기!
            
            7. 수정화면(링크처리)
                - 메서드명 : updateForm
                - 매개변수 : @RequestParam으로 m_number만 요청하고 모델값 요청
                - MemberDTO members = ms.findById(m_number) 객체에 해당 메서드 담기
                - model.addAttribute("members", members) -> 화면출력 용도!
                - 리턴값은 update 페이지로
            
            8. 수정처리(데이터 처리)
                 - update에서 post로 보냈기 때문에 메서드는 post 방식
                - 매개변수는 @ModelAttribute(전체 호출) MemberDTO(회원정보) members(객체이름), 모델값 요청, throws IllegalStateException, IOException (사진 예외처리)
                - ms.update(members); -> 서비스로 보내줌
                - String result ="redirect:/member/mypage?m_number=" + members.getM_number(); 
                     - 마이페이지에서 회원 개개인의 정보 상세조회 하기 위한 url 경로
                - model.addAttribute("members", members) -> 화면출력 용도!
                - 리턴값은 result 변수값
            
            9. 아이디 중복체크
                - post 방식 처리
                - 아이디 중복체크니까 @RequestParam으로 m_id 요청
                - String 타입요청?,(function 함수에서 만들어준 result를 넘겨주기위해 @ResponseBody 어노테이션 사용
                - String result = ms.idDuplicate(m_id) -> 우변 담아주고
                - 리턴타입 result로 보내주기
    
    
    - BoardController
       - board 폴더안에 있는 모든 jsp 관리 및 메서드 생성 역할
        - 처음에 어노테이션 생성 @Controller
        - @Autowired 이용하여 BoardService,CommentService 호출
            
            1. 글쓰기 링크(GET)를 눌렀을 때 처리해주는 saveForm 메서드 생성, 리턴값은 save(글쓰기페이지)로 보내주기
            
            2. save(글쓰기페이지)를 처리해주는 메서드 생성
                - save에서 post로 보냈기 때문에 메서드는 post 방식
                - 매개변수는 @ModelAttribute(전체 호출) MemberDTO(회원정보) members(객체이름), throws IllegalStateException, IOException (사진 예외처리)
                - bs.save(board) -> 서비스로 전달
                - 리턴값은 "redirect:/board/paging" -> 페이징 처리되는 글목록으로 보내기(파일이 아니고 주소이므로 리다이렉트)
            
            3. 글목록 처리
                - 변수는 모델값 담아주기 -> 출력을 위해
                 - 전체목록 출력해야하니 DTO를 리스트에 담아주고 bs.findAll() 넘겨주기
                - model.addAttribute("bList", bList) -> 화면출력 기능
                - 리턴은 findAll 페이지로
            
            4. 회원목록 처리
                - 매개변수는 model값
                - 전체목록 출력해야하니 DTO를 리스트에 담아주고 ms.findAll() 넘겨주기
                - model.addAttribute("mList", mList) -> 화면출력 기능
                - 리턴은 findAll 페이지
            
            5. 상세조회 처리
                - 메서드명 : findById
                - 매개변수 : @RequestParam으로 m_number만 요청하고 모델값 요청,
                - @RequestParam(value="page", required=false, defaultValue="1")int page 
                    - value : 파라미터 이름, requried : 필수여부 , defaultValue : 기본값(page 요청값이 없으면 1로 세팅,)
                - BoardDTO board = bs.findById(b_number); 객체에 해당 메서드 담기
                - model.addAttribute("board", board); -> 게시글DTO 담고!
                - model.addAttribute("page", page); -> 페이지DTO 담고!
                - board detail에 댓글 내용 추가해야하니 절차 알아보기
                    - comment.service_findAll(전체목록)을 b_number로 받고 CommentDTO를 리스트에 담아서 commentList 변수지정
                    - 모델값으로 commentList 찍어주기
                    - 리턴값은 board/detail !
            
            6. 삭제 처리
                - 매개변수 : @RequestParam으로 b_number만 요청하고 모델값 요청
                - bs.delete(m_number) -> 서비스로 보내주기
                - 회원탈퇴되면 전체목록으로 가기!
            
            7. 수정화면(링크처리)
                - 메서드명 : updateForm
                - model.addAttribute("board", board); -> 게시글DTO 담고!
                - model.addAttribute("page", page); -> 페이지DTO 담고!
           
            8. 수정처리(데이터 처리)
                - 매개변수 : @@ModelAttribute로 board 객체,page 요청 그리고 사진첨부를 위한 예외 처리 중요!
                - 중요한점 다시 돌아가는 url 잘 설정하기!
                    - return "redirect:/board/detail?b_number="+board.getB_number()+ "&page=" + page;	
            
            9. 페이징 처리
                - page, 모델값 요청 게시판 DTO를 리스트에 담고 페이징 결과 담기
                - 모델값으로 리스트,페이징 출력
           
            10. 검색결과 처리
                - @RequestParam으로 searchtype,keyword 모델값 요청
                - 상기 두개 변수 리스트에 담고!
      
     
      - CommentController
        - 처음에 어노테이션 생성 @Controller
        - @Autowired 이용하여 CommentService 호출
           
            1. 댓글작성,목록 처리하는 메서드 생성 및 관리(post 방식!)
           
            2. save(댓글쓰기페이지)를 처리해주는 메서드 생성
                - List<CommentDTO> 타입의 commentList를 리턴해주기 때문에 @ResponseBody 어노테이션 사용
                - List<CommentDTO> commentList = cs.findAll(comment.getB_number());
                    - 우변(comment 전체목록중 해당 게시글에 속하는)을 DTO에 담아줌
            
            3. delete(댓글 삭제)를 처리해주는 메서드 생성
                - 매개변수는 게시판번호,댓글번호,페이지번호를 받고
                - result 값에 담아서 리턴
### com.icia.board.dto
    - MemberDTO
       
        1. 회원번호, 회원 아이디, 회원 비밀번호, 회원이름, 회원 이메일, 회원 전화번호
        
        2. 사진 올리기 위해서 2가지 필드 선언
            - MultipartFile 타입의 m_file
            - String 타입의 m_filename
        
        3. @Data 어노테이션 필수!
    
    - BoardDTO
       
        1. 게시판번호, 게시판 작성자, 게시판 비밀번호, 게시판 제목, 게시판 내용,게시판 조회수, 게시판 작성일,사진 첨부(상기 참고), 참조를 위한 회원 아이디
       
        2. 게시판번호만 롱타입!
        
        3. 게시판 작성일 Timestamp 타입!! -> java.sql.Timestamp로 import 잘하기!
    
    - CommentDTO
        
        1. 댓글번호, 회원번호, 댓글 작성자, 댓글 내용, 댓글 작성일
       
        2. 번호는 롱타입!
        
        3. 댓글 작성일 Timestamp 타입!! -> java.sql.Timestamp로 import 잘하기!

    - PageDTO
        1. 페이지, 시작페이지, 최대페이지, 끝페이지 필드선언
### com.icia.board.service
    - 시작은 항상 @Service 어노테이션 생성(클래스 바로위에)
    - @Autowired 어노테이션을 사용하여 MemberRepository와 HttpSession(세션값) 호출
    - 어노테이션은 밑에 한줄만 적용가능, 어노테이션 밑에 두 줄 불가능
    
    - MemberService
        1. 회원가입 처리 메서드
            - 저장경로 확인 필수(특히 현재 프로젝트명과 일치하는지)
            - 사진 저장 처리 시 2가지 절차 필수
                - file import -> java.io.File;
                - m_file.transferTo(new File(savePath)); -> 예외처리 던지기

        2. 로그인 처리
            - session.setAttribute를 활용하여 로그인(아이디) 정보를 세션에 저장
            - 로그인이 되면 페이징된 글목록으로 보내기("redirect:/board/paging";)

        3. 회원전체목록 처리
            - DTO를 리스트에 담아서 객체 생성 후 리턴(foreach 사용 전체 출력
            )
        
        4. 상세조회, 삭제,업데이트 처리 컨트롤러와 비슷함

        5. 아이디 중복체크
            - 매개변수를 아이디로 지정
            - ajax로 생성했던 idDuplicate를 활용하여 리턴값을 중복이면 no 아니면 ok

        6.  마이페이지 처리
            - 메서드 타입은 MemberDTO
            - 매개변수는 회원번호
    
    - BoardService(인터페이스)
        1. 글쓰기,상세조회 처리 -> 회원가입과 동일
        2. 페이징 처리!
            - pagingList(List<BoardDTO> 타입), paging(PageDTO 타입) 두가지 메서드 생성
               
                - pagingList 메서드
                     - private static final int PAGE_LIMIT = 5; -> 한페이지에 보여질 글 개수
                    - private static final int BLOCK_LIMIT = 3; -> 한화면에 보여질 페이지 개수
                    - int pagingStart = (page - 1) * PAGE_LIMIT; ->  1페이지 limit 0,3, 2페이지 3 ,3, 3페이지 6,3
                    - Map<String, Integer> pagingParam = new HashMap<String, Integer>() -> 맵기능을 활용하여 객체를 생성, 매개변수로 스트링,인트 타입
                    - 	pagingParam.put("start", pagingStart) ,pagingParam.put
                    ("limit", PAGE_LIMIT) -> 시작 과 제한을 설정해주기
                    - List<BoardDTO> pagingList = br.pagingList1(pagingParam) -> 리스트에 객체값 담아주고
                    - foreach로 객체 출력, 리턴은 객체로
                
                - paging 메서드
                    - 총 페이지 계산(요청페이지를 잘 생각하기)
                    - int boardCount = br.boardCount() -> 전체 글 갯수 조회
                    - int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT)) -> 전체 페이지 계산 , ceil 무조건 올림(6.1은 7페이지)
                    - 시작페이지, 끝페이지 각각 변수 설정해주기
                    - if문 사용 조건식
                    - paging 객체선언
                    - 페이징 객체에 set으로 데이터 담아주기, 리턴값은 paging

            3. 검색기능 처리
                - 메서트 타입 : List<BoardDTO> 
                - 매개변수 : searchtype, keyword -> 스트링값
                - 맵기능 활용 객체 선언
                - put으로 데이터 넣어주기
                - 리턴값은 리스트 객체(bList)

            4. 댓글작성
                - 메서드 타입 : void
                - 매개변수 : DTO 타입

    - CommentService(인터페이스)
        1. 댓글작성, 댓글목록, 댓글삭제 기능 처리
        2. 삭제시 해당 경로로 이동
            - return "redirect:/board/detail?b_number="+b_number+"&page="+page
                - 주소 오타없이 잘 확인하기!
### com.icia.member.repository
    - MemberRepository
        - @Repository 필수!
        - @Autowired 활용하여 SqlSessionTemplate sql 호출 -> sql 구문 활용을 위해
        - namespace, id 잘 확인하기
            1. 회원가입 처리
                - int 타입, insert 구문 사용
                - DTO 객체 담아주기

            2. 회원로그인 처리
                - selectOne 구문 사용 -> 한가지만 출력하기 위해
                - 객체 담아주기

            3. 회원 전체목록
                - selectList 구문 사용 -> 전체목록 출력
                - List<MemberDTO> 타입

            4. 회원 상세조회
                - 매개변수 : 회원번호
                - selectOne 구문 사용 -> 한가지만 출력하기 위해

            5. 삭제,수정만 업데이트, 삭제 구문 사용하기

        - BoardRepository
            1. 상세조회 메서드에서 수정시 글상세조회로 가는 쿼리문만 추가
            2. 페이징, 검색, 댓글작성에 대한 쿼리문(구문) 작성 

        - CommentRepository
            1. 특히 사항 없음
            2. 댓글 작성,목록,삭제 쿼리문(구문) 작성

### com.icia.board.repository.mapper
    - mapper는 회원,게시글,댓글 3가지로 구분해준다
    - update,delete,insert,select 쿼리문 사용해주기
    - 특히 mapper 중복 일어나지 않도록 주의 -> 404뜸
    - value 값 오타 주의하고 테이블명 오타 주의
    - id, parameterType, resultType 잘 생각해서 넣어주기

## view 폴더
    - index,jsp 그리고 member,board 폴더가 존재함


### member 폴더
     1. insert.jsp
        - 회원가입페이지
        - action 경로를 잘보고 방식은 post
        - input 태그 사용
        - 사진첨부를 위해서는 form 시작태그에 필히 enctype="multipart/form-data" 추가
        - name값 필수 지정(DTO 필드이름,DB 컬럼이름)
        -script - ajax를 활용하여 아이디 중복체크(idDuplicate)
            - 타입 post , url: 'idDuplicate' data는 아이디, if을 활용하여 성공,실패 구분

    2. login.jsp
        - 아이디, 비밀번호 두가지만 받을 수 있도록 설정
        - action 값 잘확인

    3. findAll.jsp
        - 회원전체목록 출력
            - c foreach 사용
            - <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 구문추가
        - model 값 찍어주기
        - 조회,삭제 수정이 가능함
  

    4. update.jsp
        - 회원수정 페이지
        - script 함수(fuction)를 활용하여 비밀번호 일치시 수정가능 기능 생성

    5. detail.jsp
        - 회원상세조회 페이지
        - model 값 찍어주기
        -삭제, 수정 가능

    6. mypage.jsp
        - script 함수(fuction)를 활용하여 로그아웃 기능 생성
        - 관리자만 해당 페이지 확인가능
            - <c:if test="${sessionScope.loginId eq m.m_id}">
            - <script src="https://code.jquery.com/jquery-3.6.0.js"></script> 구문 추가
        - model 값 찍어주기
        
### board 폴더
    1. save.jsp
        - 글쓰기 페이지
        - action 경로를 잘보고 방식은 post
        - input 태그 사용
        - 사진첨부를 위해서는 form 시작태그에 필히 enctype="multipart/form-data" 추가
        - name값 필수 지정(DTO 필드이름,DB 컬럼이름)
    
    2. findAll.jsp
        - 글전체목록 출력
        - 검색 form, c 태그를 활용하여 페이징 처리, c foreach를 활용한 model값 출력
        - 조회,삭제 수정이 가능함
        - 글목록 관리자만 보임(c if)

    3. detail.jsp
        - 글상세조회 페이지
        - div 영역 지정 댓글작성, 댓글 출력 나누기(테이블 사용)
        - script - ajax를 활용하여 댓글작성
        - 댓글삭제시 가능경로 잘 확인!!
            - "/comment/delete?b_number=${board.b_number}&page=${page}&c_number=${comment.c_number}"
        - 작성자는 수정,삭제 가능 / 관리자는 삭제만

     4. update.jsp
        - 글수정 페이지
        - script 함수(fuction)를 활용하여 비밀번호 일치시 수정가능 기능 생성





                




