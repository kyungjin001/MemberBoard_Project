package com.icia.project.service;


import com.icia.project.dto.CommentDetailDTO;
import com.icia.project.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);

    void deleteById(Long commentId);
}
