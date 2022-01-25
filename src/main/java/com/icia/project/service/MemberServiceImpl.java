package com.icia.project.service;


import com.icia.project.dto.MemberDetailDTO;
import com.icia.project.dto.MemberLoginDTO;
import com.icia.project.dto.MemberSaveDTO;
import com.icia.project.entity.MemberEntity;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {



    private final MemberRepository mr;



    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile memberImage = memberSaveDTO.getMemberImage();
        String memberImageName = memberImage.getOriginalFilename();
        memberImageName = System.currentTimeMillis() + "-" + memberImageName;
        String savePath = "C:\\development\\source\\springboot\\MemberBoard\\src\\main\\resources\\static\\upload\\"+memberImageName;
        if(!memberImage.isEmpty()) {
            memberImage.transferTo(new File(savePath));
        }
        memberSaveDTO.setMemberImageName(memberImageName);


        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        mr.save(memberEntity).getId();
        Long result =  mr.save(memberEntity).getId();
        System.out.println("이유가머임?"+ result);
        return result;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if(memberEntity != null){
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())){
                return true;
            }else {
                return false;
            }}
        else {
            return false;
        }
    }

    @Override
    public String idDuplicate(String memberEmail) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
        if (memberEntity==null){
            return "ok";
        }
        else{
            return "no";
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        // List<MemberEntity> -> List<MemberDetailDTO>
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for (MemberEntity m : memberEntityList)
        {
            // Entitiy 객체를 MemberDetailDTO로 변환하고 memberList에 다음.
            memberList.add(MemberDetailDTO.toMemberDetailDTO(m)); //한줄로 가능
//            MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(m);
//            memberList.add(memberDetailDTO);
        }
        System.out.println(memberList);
        return memberList;
    }


    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

    @Override
    public MemberDetailDTO myPage(long memberId) {
        MemberEntity memberEntity = mr.findById(memberId);
        MemberDetailDTO memberDetailDTO =MemberDetailDTO.toMemberDetailDTO(memberEntity);

        return memberDetailDTO;
    }


    @Override
    public MemberDetailDTO findById(Long memberId) {
        Optional<MemberEntity> memberEntityOptional = mr.findById(memberId);
        MemberEntity memberEntity = memberEntityOptional.get();
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity); //변환 모습
//        return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
        return memberDetailDTO;
    }


    @Override
    public MemberDetailDTO findByEmail(String memberEmail) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
        MemberDetailDTO memberDetailDTO =MemberDetailDTO.toMemberDetailDTO(memberEntity);
        return memberDetailDTO;
    }

    @Override
    public Long update(MemberDetailDTO memberDetailDTO) throws IOException { //mr에서는 무조건 MemberEntity를 넘겨줘야함
        MultipartFile memberImage = memberDetailDTO.getMemberImage();
        String memberImageName = memberImage.getOriginalFilename();
        memberImageName = System.currentTimeMillis() + "-" + memberImageName;
        String savePath = "C:\\development\\source\\springboot\\MemberBoard\\src\\main\\resources\\static\\upload\\"+memberImageName;
        if(!memberImage.isEmpty()) {
            memberImage.transferTo(new File(savePath));
        }
        memberDetailDTO.setMemberImageName(memberImageName);
        MemberEntity memberEntity = MemberEntity.toupdateMember(memberDetailDTO);
        Long memberId = mr.save(memberEntity).getId();
        return memberId;
    }


}
