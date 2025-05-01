package com.meshcraft.groundcommunity.business

import com.meshcraft.groundcommunity.core.command.ArticleCommandHandler
import com.meshcraft.groundcommunity.core.entity.Article
import com.meshcraft.groundcommunity.core.entity.CommunityCategory
import com.meshcraft.groundcommunity.core.query.CategoryQueryHandler
import com.meshcraft.groundcommunity.core.repository.ArticleRepository
import com.meshcraft.groundcommunity.interfaces.dto.ArticleCreateDto
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.kotest.matchers.shouldBe

class ArticleRegisterCommandHandlerTest : BehaviorSpec() {

    private val categoryQueryHandler = mockk<CategoryQueryHandler>()
    private val articleRepository = mockk<ArticleRepository>(relaxed = true)
    private val articleCommandHandler = ArticleCommandHandler(
        articleRepository = articleRepository,
        categoryQueryHandler = categoryQueryHandler
    )

    private val articleSlot = slot<Article>()

    init {

        every { articleRepository.save(capture(articleSlot)) } answers { articleSlot.captured }

        val testCategory = CommunityCategory.create(name = "Test Category", type = "Test Type")
        every { categoryQueryHandler.getCategoryBy(any()) } returns testCategory

        Given("Article creation request") {
            val categoryId = 1L
            val authorId = 1L

            val dto = ArticleCreateDto(
                title = "Test Title",
                content = "Test Content",
                tags = listOf("tag1", "tag2"),
                communityCategoryId = categoryId,
                isPublic = true,
                isBanned = false,
                authorId = authorId
            )

            When("createArticle is called") {
                articleCommandHandler.createArticle(dto)

                Then("repository's save method should be called with correct data") {
                    verify { articleRepository.save(any()) }

                    with(articleSlot.captured) {
                            title shouldBe "Test Title"
                            content shouldBe "Test Content"
                            tags.map { it.name } shouldContainExactlyInAnyOrder listOf("tag1", "tag2")
                            isPublic shouldBe true
                            isBanned shouldBe false
                            communityCategory shouldBe testCategory
                            authorId shouldBe authorId
                    }
                }
            }
        }
    }
}