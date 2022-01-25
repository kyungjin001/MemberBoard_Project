package com.icia.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDTO {

    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private MultipartFile memberImage;
    private String memberImageName;
    private LocalDateTime memberTime;


}
