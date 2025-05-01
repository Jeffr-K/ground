package com.meshcraft.groundcommunity.interfaces.controller

import com.meshcraft.groundcommunity.core.command.ArticleCommandHandler
import com.meshcraft.groundcommunity.core.query.ArticleQueryHandler
import com.meshcraft.groundcommunity.interfaces.dto.ArticleCreateDto
import com.meshcraft.groundcommunity.interfaces.dto.ArticleEditDto
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController(
    private val articleCommandHandler: ArticleCommandHandler,
    private val articleQueryHandler: ArticleQueryHandler
) {
    @GetMapping
    fun articles(
        @RequestParam(defaultValue = "0") @Valid page: Int,
        @RequestParam(defaultValue = "10") @Valid size: Int,
        @RequestParam(defaultValue = "createdAt") @Valid orderBy: String
    ) {
        articleQueryHandler.getArticles()
    }

    @GetMapping("/{id}")
    fun article(@PathVariable @Valid id: Long) {
        articleQueryHandler.getArticle(id)
    }

    @PostMapping
    fun createArticle(@RequestBody @Valid articleCreateDto: ArticleCreateDto) {
        articleCommandHandler.createArticle(dto = articleCreateDto)
    }

    @PutMapping("/{articleId}")
    fun editArticle(
        @PathVariable @Valid articleId: Long,
        @RequestBody @Valid articleEditDto: ArticleEditDto
    ) {
        articleCommandHandler.editArticle(
            articleId = articleId,
            dto = articleEditDto
        )
    }

    @DeleteMapping("/{articleId}")
    fun deleteArticle(@PathVariable @Valid articleId: Long) {
        articleCommandHandler.deleteArticle(articleId = articleId)
    }
}