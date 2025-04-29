package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.*

@Entity
class Tag(
    @Column(nullable = false)
    val name: String,

    @ManyToMany(mappedBy = "tags")
    val articles: MutableSet<Article> = mutableSetOf(),

    @ManyToMany(mappedBy = "tags")
    val feeds: MutableSet<Feed> = mutableSetOf()
) : EntitySpec() {
    companion object {
        fun create(name: String): Tag {
            return Tag(name = name)
        }
    }

    fun addToArticle(article: Article) {
        articles.add(article)
        article.tags.add(this)
    }

    fun addToFeed(feed: Feed) {
        feeds.add(feed)
        feed.tags.add(this)
    }

    fun removeFromArticle(article: Article) {
        articles.remove(article)
        article.tags.remove(this)
    }

    fun removeFromFeed(feed: Feed) {
        feeds.remove(feed)
        feed.tags.remove(this)
    }

    fun getUsageCount(): Int {
        return articles.size + feeds.size
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tag) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}