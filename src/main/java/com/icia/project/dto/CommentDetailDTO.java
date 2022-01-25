package com.icia.project.dto;

import com.icia.project.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDetailDTO {
    private Long commentId;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentTime;

    public static CommentDetailDTO toCommentDetailDTO(CommentEntity commentEntity) {
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentId(commentEntity.getId());
        commentDetailDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDetailDTO.setCommentContents(commentEntity.getCommentContents());
        if(commentEntity.getUpdateTime()==null){
            commentDetailDTO.setCommentTime(commentEntity.getCreateTime());
        }else {
            commentDetailDTO.setCommentTime(commentEntity.getUpdateTime());
        }
        commentDetailDTO.setBoardId(commentEntity.getBoardEntity().getId());
        return commentDetailDTO;
    }
}
