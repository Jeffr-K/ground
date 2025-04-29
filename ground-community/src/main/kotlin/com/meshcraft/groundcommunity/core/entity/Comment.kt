package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcommunity.core.value.ReferenceType
import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime

@Entity
class Comment(
    @Column(nullable = false, length = 5000)
    val content: String,

    @Column(nullable = false)
    val authorId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val referenceType: ReferenceType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    val feed: Feed? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    val article: Article? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Comment? = null,

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    val replies: MutableList<Comment> = mutableListOf()
) : EntitySpec() {

    companion object {
        fun create(
            content: String,
            authorId: Long,
            referenceType: ReferenceType,
            feed: Feed? = null,
            article: Article? = null
        ): Comment {
            return when(referenceType) {
                ReferenceType.FEED -> Comment(
                    content = content,
                    authorId = authorId,
                    referenceType = ReferenceType.FEED,
                    feed = feed
                )
                ReferenceType.ARTICLE -> Comment(
                    content = content,
                    authorId = authorId,
                    referenceType = ReferenceType.ARTICLE,
                    article = article
                )
            }
        }

        fun createReply(
            content: String,
            authorId: Long,
            parentComment: Comment
        ): Comment {
            return when (parentComment.referenceType) {
                ReferenceType.FEED -> Comment(
                    content = content,
                    authorId = authorId,
                    referenceType = ReferenceType.FEED,
                    feed = parentComment.feed!!,
                    parent = parentComment
                )
                ReferenceType.ARTICLE -> Comment(
                    content = content,
                    authorId = authorId,
                    referenceType = ReferenceType.ARTICLE,
                    article = parentComment.article!!,
                    parent = parentComment
                )
            }
        }
    }

    fun addReply(reply: Comment) {
        replies.add(reply)
    }

    fun isReply(): Boolean {
        return parent != null
    }

    fun getReplyCount(): Int {
        return replies.size
    }
}