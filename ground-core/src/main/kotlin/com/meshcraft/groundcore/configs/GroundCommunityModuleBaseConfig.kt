package com.meshcraft.groundcore.configs

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ConditionalOnClass(name = ["com.meshcraft.groundcommunity.GroundCommunityApplication"])
@EntityScan(basePackages = ["com.meshcraft.groundcommunity.entity"])
@EnableJpaRepositories(basePackages = ["com.meshcraft.groundcommunity.repository"])
class GroundCommunityModuleBaseConfig {
}