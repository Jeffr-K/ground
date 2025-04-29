package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcommunity.core.value.ReferenceType
import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.*

@Entity
class Attachment(
    @Column(nullable = false)
    val filename: String,

    @Column(nullable = false)
    val fileType: String,

    @Column(nullable = false)
    val fileSize: Long,

    @Column(nullable = false)
    val fileId: Long,

    @Column(nullable = false)
    val authorId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val referenceType: ReferenceType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId")
    val feed: Feed? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    val article: Article? = null
) : EntitySpec() {
    companion object {
        fun createForFeed(
            filename: String,
            fileType: String,
            fileSize: Long,
            fileId: Long,
            authorId: Long,
            feed: Feed
        ): Attachment {
            return Attachment(
                filename = filename,
                fileType = fileType,
                fileSize = fileSize,
                fileId = fileId,
                authorId = authorId,
                referenceType = ReferenceType.FEED,
                feed = feed
            )
        }

        fun createForArticle(
            filename: String,
            fileType: String,
            fileSize: Long,
            fileId: Long,
            authorId: Long,
            article: Article
        ): Attachment {
            return Attachment(
                filename = filename,
                fileType = fileType,
                fileSize = fileSize,
                fileId = fileId,
                authorId = authorId,
                referenceType = ReferenceType.ARTICLE,
                article = article
            )
        }
    }
}