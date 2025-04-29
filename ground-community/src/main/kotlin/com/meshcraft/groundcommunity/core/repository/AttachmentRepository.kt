package com.meshcraft.groundcommunity.core.repository

import com.meshcraft.groundcommunity.core.entity.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository : JpaRepository<Attachment, Long> {
}