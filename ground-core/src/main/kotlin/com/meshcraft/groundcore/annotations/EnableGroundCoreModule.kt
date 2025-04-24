package com.meshcraft.groundcore.annotations

import com.meshcraft.groundcore.configs.*
import org.springframework.context.annotation.Import
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@Import(
    GroundCoreModuleBaseConfig::class,
//    GroundAlarmModuleBaseConfig::class,
//    GroundAnalyticsModuleBaseConfig::class,
//    GroundCommunityModuleBaseConfig::class,
//    GroundConversationModuleBaseConfig::class,
//    GroundMatchModuleBaseConfig::class,
//    GroundPaymentModuleBaseConfig::class,
//    GroundProfileModuleBaseConfig::class,
//    GroundSearchModuleBaseConfig::class,
//    GroundSpaceModuleBaseConfig::class
)
annotation class EnableGroundCoreModule
