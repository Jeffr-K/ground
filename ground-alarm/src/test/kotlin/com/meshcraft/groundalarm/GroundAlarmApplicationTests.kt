package com.meshcraft.groundalarm

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.profiles.active=test"])
class GroundAlarmApplicationTests {

    @Test
    fun contextLoads() {
    }

}
