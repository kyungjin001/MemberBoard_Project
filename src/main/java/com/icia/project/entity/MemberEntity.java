package com.icia.project.entity;


import com.icia.project.dto.MemberDetailDTO;
import com.icia.project.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private  String memberPassword;

    @Column
    private String memberName;

    @Column(unique = true)
    private String memberPhone;

    @Column
    private String memberImageName;


    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY) //일대다
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 회원 : 댓글 = 1 : N 관계
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.PERSIST,orphanRemoval = false,fetch = FetchType.LAZY) //일대다
    private List<CommentEntity> commentEntityList = new ArrayList<>();



    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPhone(memberSaveDTO.getMemberPhone());
        memberEntity.setMemberImageName(memberSaveDTO.getMemberImageName());
        return memberEntity;
    }


//    @PreRemove
//    private void preRemove(){
//        System.out.println("MemberEntity.preRemove");
//        boardEntityList.forEach(board -> board.setMemberEntity(null));
////        for(BoardEntity board : boardEntityList){
////            board.setMemberEntity(null); //위랑 동일 코드
////        }
//        commentEntityList.forEach(comment -> comment.setMemberEntity(null));
//    }


    // MemberDetailDTO -> MemberEntity 객체로 변환하기 위한 메서드
    public static MemberEntity toupdateMember(MemberDetailDTO memberDetailDTO) {
        // MemberEntity타입의 객체를 보내기 위해 memberEntity라는 객체 선언
        MemberEntity memberEntity = new MemberEntity();

        // memberEntity 객체에 memberDetailDTO 객체 안에 담긴 각 요소를 담아줌.
        memberEntity.setId(memberDetailDTO.getMemberId());
        memberEntity.setMemberEmail(memberDetailDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDetailDTO.getMemberPassword());
        memberEntity.setMemberName(memberDetailDTO.getMemberName());
        memberEntity.setMemberPhone(memberDetailDTO.getMemberPhone());
        memberEntity.setMemberImageName(memberDetailDTO.getMemberImageName());

        // 변환이 완료된 memberEntity 객체를 넘겨줌
        return memberEntity;
    }







}
