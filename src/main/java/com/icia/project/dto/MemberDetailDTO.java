package com.icia.project.dto;


import com.icia.project.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDTO {


    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private MultipartFile memberImage;
    private String memberImageName;
    private LocalDateTime memberTime;


    public static MemberDetailDTO toMemberDetailDTO(MemberEntity member){
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(member.getId());
        memberDetailDTO.setMemberEmail(member.getMemberEmail());
        memberDetailDTO.setMemberPassword(member.getMemberPassword());
        memberDetailDTO.setMemberName(member.getMemberName());
        memberDetailDTO.setMemberPhone(member.getMemberPhone());
        memberDetailDTO.setMemberImageName(member.getMemberImageName());
        if(member.getUpdateTime()==null){
            memberDetailDTO.setMemberTime(member.getCreateTime());
        }else {
            memberDetailDTO.setMemberTime(member.getUpdateTime());
        }
        return memberDetailDTO;
    }

}
