package com.icia.project.controller;

import com.icia.project.dto.MemberDetailDTO;
import com.icia.project.dto.MemberLoginDTO;
import com.icia.project.dto.MemberSaveDTO;
import com.icia.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.icia.project.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService ms;


    @GetMapping("save")
    public String saveForm() {
        return "member/save";
    }


    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws IOException {
        Long memberId = ms.save(memberSaveDTO);
        System.out.println("memberSaveDTO = " + memberSaveDTO);
        return "index";
    }


    @GetMapping("login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session){
//                        @RequestParam(defaultValue = "/") String redirectURL)
        System.out.println("MemberController.login"); //soum
//        System.out.println("redirectURL = " + redirectURL); //soup
        boolean loginResult = ms.login(memberLoginDTO);
        if (loginResult) {
            MemberDetailDTO memberDetailDTO = ms.findByEmail(memberLoginDTO.getMemberEmail());
            session.setAttribute("loginEmail", memberLoginDTO.getMemberEmail());
            session.setAttribute("loginId", memberDetailDTO.getMemberId());
            session.setAttribute(LOGIN_EMAIL, memberLoginDTO.getMemberEmail());
//        return "redirect:/member/"; //다시 컨트롤러로 목록 요청해야 하는까 목록은 리다이렉트~
//            return "member/myPage";
//            return "redirect:/board";
            String redirectURL = (String) session.getAttribute("redirectURL");
            if (redirectURL != null){
                return "redirect:" + redirectURL; // 사용자가 요청한 주소로 보내주기 위해
            }else {
                return "redirect:/";
            }

        } else {
            return "member/login";

        }
    }






    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //	//아이디 중복체크
    @PostMapping("idDuplicate") //에이작스는 제발 ResponseBody좀 써주자 (String 타입의 문자를 그대로 보내주는 역할)
    public @ResponseBody String idDuplicate(@RequestParam("memberEmail") String memberEmail) {

        String result = ms.idDuplicate(memberEmail);
        System.out.println(result);
        return result;
    }

    //회원목록
    @GetMapping
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

    //회원삭제(/member/delete/5)
    @GetMapping("delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return "redirect:/member/";
    }


    //회원조회(/member/5)
    @GetMapping("{memberId}")
    public String findById(@PathVariable("memberId") Long memberId, Model model) {
        MemberDetailDTO member = ms.findById(memberId);
        model.addAttribute("member", member);
        return "member/findById";
    }


    //회원조회(ajax)
    @PostMapping("{memberId}")
    public @ResponseBody
    MemberDetailDTO detail(@PathVariable("memberId") Long memberId) {
        MemberDetailDTO member = ms.findById(memberId);
        return member;
    }


    //회원삭제(/member/5)
    @DeleteMapping("{memberId}")
    public ResponseEntity deleteById2(@PathVariable Long memberId) {
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //mypage
    @GetMapping("myPage/{memberId}") //PathVariable 주소의 변수값을 가지고 오기위해(memberId)
    public String myPage(@PathVariable long memberId, Model model)  {

        MemberDetailDTO member = ms.myPage(memberId);

        model.addAttribute("member", member);
        System.out.println("제발좀 읽어라"+ memberId);
        return "member/myPage";
    }



    //수정화면 요청
    @GetMapping("update/{memberId}")
    public String updateForm(@PathVariable Long memberId ,Model model, HttpSession session) {
        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL); //타입이 다르기때문에 강제 형변환(object -> String)
        MemberDetailDTO member = ms.findByEmail(memberEmail);
        model.addAttribute("member", member);
        return "member/update";
    }


    //수정처리(post)
    @PostMapping("update")
    public String update(@ModelAttribute MemberDetailDTO memberDetailDTO) throws IOException {
        Long memberId = ms.update(memberDetailDTO);
        System.out.println(memberDetailDTO);
        //수정완료 후 해당회원의 상세페이지 출력
        return "redirect:/member/" + memberDetailDTO.getMemberId();
    }

    //수정처리(put)
    @PutMapping("{memberId}")
    //json으로 데이터가 전달되며 @RequestBody로 받아줘야함..
    public ResponseEntity update2(@RequestBody MemberDetailDTO memberDetailDTO) throws IOException {
        Long memberId = ms.update(memberDetailDTO);
        return new ResponseEntity(HttpStatus.OK);
    }









}
