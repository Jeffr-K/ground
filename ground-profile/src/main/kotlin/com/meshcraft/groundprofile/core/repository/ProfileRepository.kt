package com.meshcraft.groundprofile.core.repository

import com.meshcraft.groundprofile.core.entity.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<Profile, Long> {
    fun findByMemberId(memberId: Long): Profile?
    fun deleteProfileByMemberId(memberId: Long)
}