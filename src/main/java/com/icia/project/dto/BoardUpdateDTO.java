package com.icia.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BoardUpdateDTO {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private MultipartFile boardImage;
    private String boardImageName;
    private LocalDateTime boardDate;


    public  BoardUpdateDTO(Long boardId, String boardWriter, String boardPassword,String boardTitle,String boardContents){
        this.boardId = boardId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
    }




}
