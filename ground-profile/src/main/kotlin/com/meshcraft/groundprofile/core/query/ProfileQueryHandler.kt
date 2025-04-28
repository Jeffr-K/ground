package com.meshcraft.groundprofile.core.query

import com.meshcraft.groundprofile.core.entity.Profile
import com.meshcraft.groundprofile.core.repository.ProfileQueryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class ProfileQueryHandler(
    private val profileQueryRepository: ProfileQueryRepository,
) {

    fun getProfiles(
        page: Int,
        size: Int,
        orderBy: String = "createdAt"
    ): Page<Profile> {
        val direction = Sort.Direction.DESC
        val pageRequest = PageRequest.of(page, size, Sort.by(direction, orderBy))
        return profileQueryRepository.findAll(pageRequest)
    }

    fun getProfile(memberId: Long): Profile {
        return profileQueryRepository.findByMemberId(memberId = memberId)
            ?: throw IllegalArgumentException("Profile not found for memberId: $memberId")
    }
}