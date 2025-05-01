package com.meshcraft.groundcommunity.interfaces.dto

data class ArticleEditDto(
    val id: Long,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isDraft: Boolean
)
