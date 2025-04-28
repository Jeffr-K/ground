package com.meshcraft.groundmember.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.Entity

@Entity
class Member(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
) : EntitySpec() {
    companion object {
        fun create(
            username: String,
            password: String,
            email: String,
            phone: String
        ): Member {
            return Member(
                username,
                password,
                email,
                phone,
            )
        }
    }
}