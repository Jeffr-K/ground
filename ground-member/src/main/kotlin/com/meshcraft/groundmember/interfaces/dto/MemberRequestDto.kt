package com.meshcraft.groundmember.interfaces.dto

data class MemberRequestDto(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
)
