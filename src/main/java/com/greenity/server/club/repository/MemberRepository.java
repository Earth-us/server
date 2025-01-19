package com.greenity.server.club.repository;

import com.greenity.server.club.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
