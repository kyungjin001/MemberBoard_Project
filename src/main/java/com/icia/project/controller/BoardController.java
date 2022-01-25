package com.icia.project.controller;


import com.icia.project.common.PagingConst;
import com.icia.project.dto.*;
import com.icia.project.entity.BoardEntity;
import com.icia.project.service.BoardService;
import com.icia.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService bs;
    private final CommentService cs;






    @GetMapping("save")
    public String saveForm(){
        return "board/save";
    }


    @PostMapping("save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
        Long boardId  =  bs.save(boardSaveDTO);
            return "index";
    }



    //글목록
    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList", boardList);
        log.info("findAll 호출");
        return "board/findAll";
    }

    // 페이징처리(/board?page=5)
    // 5번글(/board/5)
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = ((((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT) + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/paging";
    }

    //글조회(/board/5)
    @GetMapping("/{boardId}")
    public String findById(Model model, @PathVariable Long boardId) {
        log.info("글보기 메서드 호출. 요청글번호: {}", boardId);
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board", board);
        List<CommentDetailDTO> commentList = cs.findAll(boardId);
        model.addAttribute("commentList", commentList);
        return "board/findById";
    }
    //
//
//    //회원조회(ajax)
    @PostMapping("/{boardId}")
    public ResponseEntity findById2(@PathVariable Long boardId) {
        BoardDetailDTO board = bs.findById(boardId);
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);
    }
    //
//
    //게시글삭제(/member/delete/5)
    @GetMapping("delete/{boardId}")
    public String deleteById(@PathVariable("boardId") Long boardId) {
        bs.deleteById(boardId);
        return "redirect:/board/";
    }
    //
//
//    //회원삭제(/member/5)
    @DeleteMapping("{boardId}")
    public ResponseEntity deleteById2(@PathVariable Long boardId) {
        bs.deleteById(boardId);

        return new ResponseEntity(HttpStatus.OK);
    }
//
//
    //수정처리
    @GetMapping("/update/{boardId}")
    public String updateForm(Model model, @PathVariable("boardId") Long boardId){
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);

        return "board/update";
    }

    //수정처리(post)
    @PostMapping("update")
    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO) throws IOException {
        bs.update(boardUpdateDTO);
        //수정완료 후 해당글의 상세페이지 출력
        return "redirect:/board/" + boardUpdateDTO.getBoardId();
    }
    //
    //수정처리(put)
    @PutMapping("{boardId}") //에이작스는 @RequestBody로 받아줘야함
    public ResponseEntity updates(@RequestBody BoardUpdateDTO boardUpdateDTO) throws IOException {
        bs.update(boardUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/search")
    public String search(@ModelAttribute BoardSearchDTO boardSearchDTO, Model model){
        List<BoardDetailDTO> boardList= bs.search(boardSearchDTO);
        model.addAttribute("boardList",boardList);
        return "board/search";
    }








}
