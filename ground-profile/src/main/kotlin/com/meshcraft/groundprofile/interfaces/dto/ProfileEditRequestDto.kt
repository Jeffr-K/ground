package com.meshcraft.groundprofile.interfaces.dto

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.validation.constraints.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ProfileEditRequestDto.Nickname::class, name = "nickname"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Description::class, name = "description"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Address::class, name = "address"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Gender::class, name = "gender"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Company::class, name = "company"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Job::class, name = "job"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Region::class, name = "region"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.MBTI::class, name = "mbti"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Smoker::class, name = "isSmoker"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Alcohol::class, name = "isAlcohol"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Height::class, name = "height"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Weight::class, name = "weight"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.Interests::class, name = "interests"),
    JsonSubTypes.Type(value = ProfileEditRequestDto.DateStyles::class, name = "dateStyles")
)
sealed class ProfileEditRequestDto {

    data class Nickname(
        @field:NotBlank(message = "닉네임은 필수입니다")
        @field:Size(min = 2, max = 30, message = "닉네임은 2자 이상 30자 이하여야 합니다")
        @field:Pattern(regexp = "^[가-힣a-zA-Z0-9\\s]*$", message = "닉네임은 한글, 영문, 숫자만 가능합니다")
        val nickname: String
    ) : ProfileEditRequestDto()

    data class Description(
        @field:Size(max = 500, message = "자기소개는 500자 이하여야 합니다")
        val description: String
    ) : ProfileEditRequestDto()

    data class Address(
        @field:NotBlank(message = "주소는 필수입니다")
        @field:Size(max = 200, message = "주소는 200자 이하여야 합니다")
        val address: String
    ) : ProfileEditRequestDto()

    data class Gender(
        @field:NotBlank(message = "성별은 필수입니다")
        @field:Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "성별은 MALE, FEMALE, OTHER 중 하나여야 합니다")
        val gender: String
    ) : ProfileEditRequestDto()

    data class Company(
        @field:Size(max = 100, message = "회사명은 100자 이하여야 합니다")
        val company: String
    ) : ProfileEditRequestDto()

    data class Job(
        @field:Size(max = 100, message = "직업은 100자 이하여야 합니다")
        val job: String
    ) : ProfileEditRequestDto()

    data class Region(
        @field:NotBlank(message = "지역은 필수입니다")
        @field:Size(max = 100, message = "지역은 100자 이하여야 합니다")
        val region: String
    ) : ProfileEditRequestDto()

    data class MBTI(
        @field:Pattern(
            regexp = "^(ISTJ|ISFJ|INFJ|INTJ|ISTP|ISFP|INFP|INTP|ESTP|ESFP|ENFP|ENTP|ESTJ|ESFJ|ENFJ|ENTJ)$",
            message = "유효한 MBTI 유형이 아닙니다"
        )
        val mbti: String
    ) : ProfileEditRequestDto()

    data class Smoker(
        @field:NotNull(message = "흡연 여부는 필수입니다")
        val isSmoker: Boolean
    ) : ProfileEditRequestDto()

    data class Alcohol(
        @field:NotNull(message = "음주 여부는 필수입니다")
        val isAlcohol: Boolean
    ) : ProfileEditRequestDto()

    data class Height(
        @field:Min(value = 100, message = "키는 100cm 이상이어야 합니다")
        @field:Max(value = 250, message = "키는 250cm 이하여야 합니다")
        val height: Int
    ) : ProfileEditRequestDto()

    data class Weight(
        @field:Min(value = 30, message = "체중은 30kg 이상이어야 합니다")
        @field:Max(value = 200, message = "체중은 200kg 이하여야 합니다")
        val weight: Int
    ) : ProfileEditRequestDto()

    data class Interests(
        @field:NotEmpty(message = "관심사는 최소 1개 이상이어야 합니다")
        @field:Size(max = 10, message = "관심사는 최대 10개까지만 등록 가능합니다")
        val interests: List<String>
    ) : ProfileEditRequestDto()

    data class DateStyles(
        @field:NotEmpty(message = "데이트 스타일은 최소 1개 이상이어야 합니다")
        @field:Size(max = 5, message = "데이트 스타일은 최대 5개까지만 등록 가능합니다")
        val dateStyles: List<String>
    ) : ProfileEditRequestDto()
}