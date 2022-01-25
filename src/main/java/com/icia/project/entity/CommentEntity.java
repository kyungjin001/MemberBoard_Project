package com.icia.project.entity;

import com.icia.project.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    // 댓글번호, 작성자, 내용, 원글
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 이거 안쓰면 이거를 모든 테이블이 공유함
    @Column(name = "comment_id")
    private Long id;






    @Column
    private String commentWriter;

    @Column
    private String commentContents;

//    @Column
//    private LocalDateTime commentTime;


    // 회원 엔티티 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 부모테이블(참조하고자하는 테이블)의 pk 컬럼이름
    private MemberEntity memberEntity; // 참조하고자 하는 테이블을 관리하는 엔티티



    // 원글의 게시글 번호를 참조하기 위한 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // 부모테이블(참조하고자하는 테이블)의 pk 컬럼이름
    private BoardEntity boardEntity; // 참조하고자 하는 테이블을 관리하는 엔티티



    public static CommentEntity toSaveEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity, MemberEntity memberEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentSaveDTO.getCommentWriter());
//        commentEntity.setCommentWriter(memberEntity.getMemberEmail());
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
//        commentEntity.setCommentTime(commentSaveDTO.getCommentTime());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);

        return commentEntity;
    }
}
