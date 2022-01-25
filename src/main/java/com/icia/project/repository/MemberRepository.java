package com.icia.project.repository;

import com.icia.project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByMemberEmail(String memberEmail);

    MemberEntity findById(long memberId);
}
