package com.meshcraft.groundcore.converter

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

object ReflectionCache {
    private val constructorCache = ConcurrentHashMap<KClass<*>, KFunction<*>>()
    private val propertiesCache = ConcurrentHashMap<KClass<*>, List<KProperty1<*, *>>>()

    fun <T : Any> getPrimaryConstructor(clazz: KClass<T>): KFunction<T> {
        @Suppress("UNCHECKED_CAST")
        return constructorCache.getOrPut(clazz) {
            clazz.primaryConstructor ?: throw IllegalArgumentException("No primary constructor found for ${clazz.simpleName}")
        } as KFunction<T>
    }

    fun <T : Any> getMemberProperties(clazz: KClass<T>): List<KProperty1<T, *>> {
        @Suppress("UNCHECKED_CAST")
        return propertiesCache.getOrPut(clazz) {
            clazz.memberProperties.toList()
        } as List<KProperty1<T, *>>
    }
}