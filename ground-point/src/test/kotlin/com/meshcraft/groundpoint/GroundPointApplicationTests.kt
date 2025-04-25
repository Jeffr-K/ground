package com.meshcraft.groundpoint

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.profiles.active=test"])
class GroundPointApplicationTests {

    @Test
    fun contextLoads() {
    }

}
