package com.meshcraft.groundcommunity.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class CommunityCategory(
    @Column
    val name: String,
    @Column
    val type: String,
    @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], orphanRemoval = false)
    val feeds: MutableList<Feed> = mutableListOf(),
    @OneToMany(mappedBy = "community", cascade = [CascadeType.ALL], orphanRemoval = false)
    val articles: MutableList<Article> = mutableListOf()
) : EntitySpec() {
    companion object {
        fun create(): CommunityCategory {
            return CommunityCategory(
                name = "Default Category",
                type = "General"
            )
        }
    }
}