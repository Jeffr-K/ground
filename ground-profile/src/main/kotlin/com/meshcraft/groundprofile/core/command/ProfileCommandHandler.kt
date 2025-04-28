package com.meshcraft.groundprofile.core.command

import com.meshcraft.groundprofile.core.repository.ProfileRepository
import com.meshcraft.groundprofile.interfaces.dto.ProfileEditRequestDto
import org.springframework.stereotype.Service

@Service
class ProfileCommandHandler(
    private val profileRepository: ProfileRepository,
) {

    // TODO: refactoring http to port
    fun editProfile(
        memberId: Long,
        dto: ProfileEditRequestDto
    ) {
        val profile = profileRepository.findByMemberId(memberId = memberId)
            ?: throw IllegalArgumentException("Profile not found for memberId: $memberId")

        when (dto) {
            is ProfileEditRequestDto.Interests -> {
                profile.edit(interests = dto.interests)
            }
            is ProfileEditRequestDto.Nickname -> {
                profile.edit(nickname = dto.nickname)
            }
            is ProfileEditRequestDto.Description -> {
                profile.edit(description = dto.description)
            }
            is ProfileEditRequestDto.Address -> {
                profile.edit(address = dto.address)
            }
            is ProfileEditRequestDto.Gender -> {
                profile.edit(gender = dto.gender)
            }
            is ProfileEditRequestDto.Company -> {
                profile.edit(company = dto.company)
            }
            is ProfileEditRequestDto.Job -> {
                profile.edit(job = dto.job)
            }
            is ProfileEditRequestDto.Region -> {
                profile.edit(region = dto.region)
            }
            is ProfileEditRequestDto.MBTI -> {
                profile.edit(mbti = dto.mbti)
            }
            is ProfileEditRequestDto.Smoker -> {
                profile.edit(isSmoker = dto.isSmoker)
            }
            is ProfileEditRequestDto.Alcohol -> {
                profile.edit(isAlcohol = dto.isAlcohol)
            }
            is ProfileEditRequestDto.Height -> {
                profile.edit(height = dto.height)
            }
            is ProfileEditRequestDto.Weight -> {
                profile.edit(weight = dto.weight)
            }
            is ProfileEditRequestDto.DateStyles -> {
                profile.edit(dateStyles = dto.dateStyles)
            }
        }

        profileRepository.save(profile)
    }
}