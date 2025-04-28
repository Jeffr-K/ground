package com.meshcraft.groundmember.core.command

data class MemberRegisterCommand(
    val username: String,
    val password: String,
    val email: String,
    val phone: String
)
