package com.meshcraft.groundcommunity.core.query

import com.meshcraft.groundcommunity.core.entity.CommunityCategory
import com.meshcraft.groundcommunity.core.repository.CommunityCategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryQueryHandler(
    private val categoryRepository: CommunityCategoryRepository
) {

    fun getCategoryBy(id: Long): CommunityCategory? {
        return categoryRepository.findById(id).orElse(null)
    }
}