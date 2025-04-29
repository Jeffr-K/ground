package com.meshcraft.groundcommunity.core.repository

import com.meshcraft.groundcommunity.core.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {
}