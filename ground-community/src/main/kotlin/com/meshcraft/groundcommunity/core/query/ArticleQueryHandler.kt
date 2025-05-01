package com.meshcraft.groundcommunity.core.query

import com.meshcraft.groundcommunity.core.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleQueryHandler(
    private val articleRepository: ArticleRepository
) {
    fun getArticles() {

    }

    fun getArticle(id: Long) {

    }
}