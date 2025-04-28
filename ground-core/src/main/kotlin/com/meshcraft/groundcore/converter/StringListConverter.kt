package com.meshcraft.groundcore.converter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {
    private val objectMapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: List<String>?): String {
        return if (attribute.isNullOrEmpty()) {
            "[]"
        } else {
            try {
                objectMapper.writeValueAsString(attribute)
            } catch (e: Exception) {
                "[]"
            }
        }
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return if (dbData.isNullOrBlank()) {
            emptyList()
        } else {
            try {
                val listType = objectMapper.typeFactory.constructCollectionType(List::class.java, String::class.java)
                objectMapper.readValue(dbData, listType)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}