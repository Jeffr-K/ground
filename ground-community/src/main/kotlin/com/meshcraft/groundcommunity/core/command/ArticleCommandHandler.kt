package com.meshcraft.groundcommunity.core.command

import com.meshcraft.groundcommunity.core.entity.Article
import com.meshcraft.groundcommunity.core.entity.Tag
import com.meshcraft.groundcommunity.core.query.CategoryQueryHandler
import com.meshcraft.groundcommunity.core.repository.ArticleRepository
import com.meshcraft.groundcommunity.interfaces.dto.ArticleCreateDto
import com.meshcraft.groundcommunity.interfaces.dto.ArticleEditDto
import org.springframework.stereotype.Service

@Service
class ArticleCommandHandler(
    private val articleRepository: ArticleRepository,
    private val categoryQueryHandler: CategoryQueryHandler
) {
    fun createArticle(dto: ArticleCreateDto) {
        val category = categoryQueryHandler.getCategoryBy(dto.communityCategoryId)
            ?: throw IllegalArgumentException("Invalid category ID: ${dto.communityCategoryId}")

        val tags = dto.tags.map { Tag.create(it) }.toMutableSet()

        val article = Article.create(
            title = dto.title,
            content = dto.content,
            tags = tags,
            communityCategory = category,
            isPublic = dto.isPublic,
            authorId = dto.authorId,
            isBanned = dto.isBanned
        )

        articleRepository.save(article)
    }

    fun editArticle(articleId: Long, dto: ArticleEditDto) {
        // Logic to edit an article
    }

    fun deleteArticle(articleId: Long) {
        // Logic to delete an article
    }
}