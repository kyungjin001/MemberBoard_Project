package com.icia.project.service;


import com.icia.project.dto.CommentDetailDTO;
import com.icia.project.dto.CommentSaveDTO;
import com.icia.project.entity.BoardEntity;
import com.icia.project.entity.CommentEntity;
import com.icia.project.entity.MemberEntity;
import com.icia.project.repository.BoardRepository;
import com.icia.project.repository.CommentRepository;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository cr;
    private final BoardRepository br; // 이거 하려면 의존성주입 어노테이션 필수!!!!!
    private final MemberRepository mr;



    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        MemberEntity memberEntity = mr.findByMemberEmail(commentSaveDTO.getCommentWriter());
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentSaveDTO, boardEntity,memberEntity);
        return cr.save(commentEntity).getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentDetailDTO> commentList = new ArrayList<>();
        for (CommentEntity c : commentEntityList){
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }

    @Override
    public void deleteById(Long commentId) {
        cr.deleteById(commentId);
    }
}
