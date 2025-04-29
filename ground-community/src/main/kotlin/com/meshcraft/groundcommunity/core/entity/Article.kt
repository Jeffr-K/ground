package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Article(
    @Column(nullable = false)
    val title: String,

    @Column(nullable = false, length = 10000)
    val content: String,

    @Column(nullable = false)
    val authorId: Long,

    @Column(nullable = false)
    val isBanned: Boolean = false,

    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "article_tags",
        joinColumns = [JoinColumn(name = "article_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: MutableSet<Tag> = mutableSetOf(),

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "communityId")
    val community: CommunityCategory,

    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true)
    val attachments: MutableList<Attachment> = mutableListOf()
) : EntitySpec() {
    companion object {
        fun create(
            title: String,
            content: String,
            authorId: Long,
            isBanned: Boolean,
            community: CommunityCategory
        ): Article {
            return Article(
                title = title,
                content = content,
                authorId = authorId,
                isBanned = isBanned,
                community = community
            )
        }
    }

    fun addAttachment(attachment: Attachment) {
        attachments.add(attachment)
    }
}