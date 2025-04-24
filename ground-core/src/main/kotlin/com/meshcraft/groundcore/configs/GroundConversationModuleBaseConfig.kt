package com.meshcraft.groundcore.configs

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ConditionalOnClass(name = ["com.meshcraft.groundconversation.GroundConversationApplication"])
@EntityScan(basePackages = ["com.meshcraft.groundconversation.entity"])
@EnableJpaRepositories(basePackages = ["com.meshcraft.groundconversation.repository"])
class GroundConversationModuleBaseConfig {
}