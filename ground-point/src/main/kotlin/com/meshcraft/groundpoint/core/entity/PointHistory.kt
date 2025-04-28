package com.meshcraft.groundpoint.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import com.meshcraft.groundpoint.core.values.PointActionType
import jakarta.persistence.*
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
class PointHistory(
    @Column(nullable = false)
    val memberId: Long,
    @Column(nullable = false)
    val amount: Int,
    @Column(nullable = false)
    @PositiveOrZero
    val balance: Int,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val actionType: PointActionType,
    @Column(nullable = true)
    val description: String? = null,
    @Column(nullable = true)
    val referenceId: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointId", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    val point: Point? = null,
) : EntitySpec() {
    companion object {
        fun create(
            memberId: Long,
            amount: Int,
            balance: Int,
            actionType: PointActionType,
            description: String? = null,
            referenceId: String? = null,
            point: Point
        ): PointHistory = PointHistory(
            memberId = memberId,
            amount = amount,
            balance = balance,
            actionType = actionType,
            description = description,
            referenceId = referenceId,
            point = point
        )
    }
}