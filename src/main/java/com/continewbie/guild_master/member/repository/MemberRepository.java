package com.continewbie.guild_master.member.repository;

import com.continewbie.guild_master.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long memberId);
    Optional<Member> findByMemberId(Long memberId);
}
