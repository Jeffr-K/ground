package com.meshcraft.groundmember.infrastructure.database

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@ConditionalOnClass(name = ["com.meshcraft.groundmember.GroundMemberApplication"])
@EntityScan(basePackages = ["com.meshcraft.groundmember.core.entity"])
@EnableJpaRepositories(basePackages = ["com.meshcraft.groundmember.core.repository"])
class DatabaseBaseConfig {}