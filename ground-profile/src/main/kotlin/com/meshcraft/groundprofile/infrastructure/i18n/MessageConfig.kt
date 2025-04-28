package com.meshcraft.groundprofile.infrastructure.i18n

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.ResourceBundleMessageSource
import java.text.MessageFormat
import java.util.*

@Configuration
class MessageConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setUseCodeAsDefaultMessage(true)
        messageSource.setCacheSeconds(3600)
        return messageSource
    }

    // YAML 메시지 소스 클래스
    class YamlMessageSource : ResourceBundleMessageSource() {
        private val yamlProcessor = YamlProcessor()

        @Throws(MissingResourceException::class)
        override fun resolveCode(code: String, locale: Locale): MessageFormat? {
            val resource = ResourceBundle.getBundle("messages", locale, YamlResourceBundleControl())

            return try {
                val message = resource.getString(code)
                MessageFormat(message)
            } catch (e: MissingResourceException) {
                super.resolveCode(code, locale)
            }
        }

        class YamlResourceBundleControl : ResourceBundle.Control() {
            override fun newBundle(
                baseName: String,
                locale: Locale,
                format: String,
                loader: ClassLoader,
                reload: Boolean
            ): ResourceBundle? {
                val bundleName = toBundleName(baseName, locale)
                val resourceName = toResourceName(bundleName, "yml")

                return try {
                    val resource = loader.getResource(resourceName)
                    if (resource != null) {
                        val objectMapper = ObjectMapper(YAMLFactory())
                        val messages = objectMapper.readValue(resource, object : TypeReference<Map<String, Any>>() {})
                        val flattenedMessages = flattenMap(messages)
                        return MapResourceBundle(flattenedMessages)
                    }
                    null
                } catch (e: Exception) {
                    null
                }
            }

            // 중첩된 YAML 맵을 평면화
            private fun flattenMap(map: Map<String, Any>, prefix: String = ""): Map<String, String> {
                return map.entries.flatMap { (key, value) ->
                    val newKey = if (prefix.isEmpty()) key else "$prefix.$key"
                    when (value) {
                        is Map<*, *> -> {
                            @Suppress("UNCHECKED_CAST")
                            flattenMap(value as Map<String, Any>, newKey).entries
                        }
                        else -> listOf(newKey to value.toString())
                    }
                }.toMap()
            }
        }

        class MapResourceBundle(private val messages: Map<String, String>) : ResourceBundle() {
            override fun handleGetObject(key: String): Any? {
                return messages[key]
            }

            override fun getKeys(): Enumeration<String> {
                return Collections.enumeration(messages.keys)
            }
        }
    }
}