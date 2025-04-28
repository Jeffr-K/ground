package com.meshcraft.groundcore.converter

import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


open class GenericServicePortConverter<C : Command, P : Port>: ServicePortConverter<C, P> {

    private val commandClass: KClass<C> by lazy {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        @Suppress("UNCHECKED_CAST") (types[0] as Class<C>).kotlin
    }

    private val portClass: KClass<P> by lazy {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        @Suppress("UNCHECKED_CAST") (types[1] as Class<P>).kotlin
    }

    override fun convert(command: C): P {
        val constructor = ReflectionCache.getPrimaryConstructor(portClass)

        val parameters = constructor.parameters
        val argumentMap = parameters.associateWith { parameter ->
            val commandProperty = commandClass.memberProperties.find { it.name == parameter.name }
            commandProperty?.get(command)
        }

        return constructor.callBy(argumentMap)
    }
}