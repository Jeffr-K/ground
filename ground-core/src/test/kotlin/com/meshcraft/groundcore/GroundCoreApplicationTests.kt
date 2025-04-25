package com.meshcraft.groundcore

import com.meshcraft.groundcore.configs.GroundCoreModuleBaseConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.ContextConfiguration
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [GroundCoreModuleBaseConfig::class])
class GroundCoreTests {
    @Test
    fun contextLoads() {
        assertTrue(true)
    }
}
