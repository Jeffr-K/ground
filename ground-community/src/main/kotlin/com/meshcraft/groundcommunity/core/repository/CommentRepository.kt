package com.meshcraft.groundcommunity.core.repository

import com.meshcraft.groundcommunity.core.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
}