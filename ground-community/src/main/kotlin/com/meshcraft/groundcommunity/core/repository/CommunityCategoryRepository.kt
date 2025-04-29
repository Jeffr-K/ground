package com.meshcraft.groundcommunity.core.repository

import com.meshcraft.groundcommunity.core.entity.CommunityCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommunityCategoryRepository : JpaRepository<CommunityCategory, Long> {}