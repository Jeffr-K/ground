package com.meshcraft.groundpoint.core.entity

import com.meshcraft.groundcore.entities.EntitySpec
import com.meshcraft.groundpoint.core.values.PointActionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.PositiveOrZero
import java.time.LocalDateTime


@Entity
class Point(
    val type: PointActionType,
    @Column(nullable = false)
    @PositiveOrZero
    val point: Int = 0,
    val memberId: Long?,
    @OneToMany(mappedBy = "point", orphanRemoval = false)
    val histories: MutableList<PointHistory> = mutableListOf()
) : EntitySpec() {
    companion object {
        fun create(pointActionType: PointActionType, point: Int, memberId: Long?): Point {
            return Point(
                pointActionType,
                point,
                memberId!!
            )
        }
    }

    fun currentPoint(): Int {
        return this.point
    }

    fun freeze(): Point {
        this.deletedAt = LocalDateTime.now()
        return this
    }
}