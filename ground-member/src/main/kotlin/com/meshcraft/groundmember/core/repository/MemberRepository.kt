package com.meshcraft.groundmember.core.repository

import com.meshcraft.groundmember.core.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {}