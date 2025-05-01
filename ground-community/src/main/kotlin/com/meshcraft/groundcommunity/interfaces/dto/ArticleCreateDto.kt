package com.meshcraft.groundcommunity.interfaces.dto

data class ArticleCreateDto(
    val title: String,
    val content: String,
    val tags: List<String>,
    val communityCategoryId: Long,
    val isPublic: Boolean,
    val isBanned: Boolean = false,
    val authorId: Long
)
