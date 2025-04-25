package com.meshcraft.groundbatch

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.profiles.active=test"])
class GroundBatchApplicationTests {

    @Test
    fun contextLoads() {
    }

}
