package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class Feed(
    @Column(nullable = false)
    val title: String,

    @Column(nullable = false, length = 10000)
    val content: String,

    @Column(nullable = false)
    val isBanned: Boolean = false,

    @Column(nullable = false)
    val authorId: Long,

    @OneToMany(mappedBy = "feed", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "feed_tags",
        joinColumns = [JoinColumn(name = "feedId")],
        inverseJoinColumns = [JoinColumn(name = "tagId")]
    )
    val tags: MutableSet<Tag> = mutableSetOf(),

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "communityId")
    val community: CommunityCategory,

    @OneToMany(mappedBy = "feed", cascade = [CascadeType.ALL], orphanRemoval = true)
    val attachments: MutableList<Attachment> = mutableListOf()
) : EntitySpec() {
    companion object {
        fun create(
            title: String,
            content: String,
            isBanned: Boolean,
            authorId: Long,
            community: CommunityCategory
        ): Feed {
            return Feed(
                title = title,
                content = content,
                isBanned = isBanned,
                authorId = authorId,
                community = community
            )
        }
    }

    fun addAttachment(attachment: Attachment) {
        attachments.add(attachment)
    }

    fun attachments(): List<Attachment> {
        return attachments
    }
}