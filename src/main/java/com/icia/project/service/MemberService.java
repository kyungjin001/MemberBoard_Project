package com.icia.project.service;

import com.icia.project.dto.MemberDetailDTO;
import com.icia.project.dto.MemberLoginDTO;
import com.icia.project.dto.MemberSaveDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    boolean login(MemberLoginDTO memberLoginDTO);

    String idDuplicate(String memberEmail);

    List<MemberDetailDTO> findAll();

    void deleteById(Long memberId);

    MemberDetailDTO myPage(long memberId);
MemberDetailDTO findById(Long memberId);


    MemberDetailDTO findByEmail(String memberEmail);

    Long update(MemberDetailDTO memberDetailDTO) throws IOException;

}
