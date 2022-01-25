package com.icia.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDTO {
    private  Long memberId;
    private  Long boardId;
    private  String commentWriter;
    private  String commentContents;
    private LocalDateTime commentTime;





}
