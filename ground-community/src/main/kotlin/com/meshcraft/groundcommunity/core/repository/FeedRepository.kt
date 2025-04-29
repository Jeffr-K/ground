package com.meshcraft.groundcommunity.core.repository

import com.meshcraft.groundcommunity.core.entity.Feed
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedRepository : JpaRepository<Feed, Long> {}