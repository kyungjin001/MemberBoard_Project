package com.icia.project.controller;


import com.icia.project.dto.CommentDetailDTO;
import com.icia.project.dto.CommentSaveDTO;
import com.icia.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor //의존성주입
public class CommentController {
    private final CommentService cs;
    @PostMapping("/save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO){
        Long commentId = cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId());
        return commentList;
    }

    //회원삭제(/member/5)
    @DeleteMapping("{commentId}")
    public ResponseEntity deleteById2(@PathVariable Long commentId) {
        cs.deleteById(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
