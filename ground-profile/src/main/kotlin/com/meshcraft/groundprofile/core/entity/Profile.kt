package com.meshcraft.groundprofile.core.entity

import com.meshcraft.groundcore.converter.StringListConverter
import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity

@Entity
class Profile(
    @Column(nullable = false)
    var nickname: String,
    @Column(length = 500)
    var description: String,
    @Column(nullable = false)
    var address: String,
    @Convert(converter = StringListConverter::class)
    @Column(columnDefinition = "TEXT")
    var interests: List<String>,
    @Column(nullable = false, unique = true)
    val memberId: Long,
    @Column
    var gender: String? = null,
    @Column
    var company: String? = null,
    @Column
    var job: String? = null,
    @Column
    var region: String? = null,
    @Column
    var mbti: String? = null,
    @Column
    var isSmoker: Boolean? = null,
    @Column
    var isAlcohol: Boolean? = null,
    @Column
    var height: Int? = null,
    @Column
    var weight: Int? = null,
    @Convert(converter = StringListConverter::class)
    @Column(columnDefinition = "TEXT")
    var dateStyles: List<String>? = null
) : EntitySpec() {
    companion object {
        fun create(
            nickname: String,
            description: String,
            address: String,
            interests: List<String>,
            memberId: Long
        ) = Profile(
            nickname,
            description,
            address,
            interests,
            memberId
        )
    }

    fun edit(
        nickname: String? = null,
        description: String? = null,
        address: String? = null,
        interests: List<String>? = null,
        gender: String? = null,
        company: String? = null,
        job: String? = null,
        region: String? = null,
        mbti: String? = null,
        isSmoker: Boolean? = null,
        isAlcohol: Boolean? = null,
        height: Int? = null,
        weight: Int? = null,
        dateStyles: List<String>? = null
    ) {
        nickname?.let { this.nickname = it }
        description?.let { this.description = it }
        address?.let { this.address = it }
        interests?.let { this.interests = it }
        gender?.let { this.gender = it }
        company?.let { this.company = it }
        job?.let { this.job = it }
        region?.let { this.region = it }
        mbti?.let { this.mbti = it }
        isSmoker?.let { this.isSmoker = it }
        isAlcohol?.let { this.isAlcohol = it }
        height?.let { this.height = it }
        weight?.let { this.weight = it }
        dateStyles?.let { this.dateStyles = it }
    }
}