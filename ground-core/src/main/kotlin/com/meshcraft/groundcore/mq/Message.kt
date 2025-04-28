package com.meshcraft.groundcore.mq

data class Message<T>(
    val id: String,
    val content: T,
    val timestamp: Long = System.currentTimeMillis()
)
