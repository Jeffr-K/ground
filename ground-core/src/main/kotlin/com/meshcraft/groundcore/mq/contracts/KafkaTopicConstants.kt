package com.meshcraft.groundcore.mq.contracts

object KafkaTopicConstants {

    private const val EVENT_PREFIX = "member"
    private const val EVENT_SUFFIX = "event"

    object Member {
        object Upstream {}

        object Downstream {
            const val REGISTERED = "$EVENT_PREFIX.registered.$EVENT_SUFFIX"
            const val WITHDRAWAL = "$EVENT_PREFIX.withdrawal.$EVENT_SUFFIX"
        }
    }

    object Point {
        object Upstream {}
        object Downstream {}
    }
}