package com.icia.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {

    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private MultipartFile boardImage;
    private String boardImageName;





}
