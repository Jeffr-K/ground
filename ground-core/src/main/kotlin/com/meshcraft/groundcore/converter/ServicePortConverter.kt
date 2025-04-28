package com.meshcraft.groundcore.converter

interface ServicePortConverter<C : Command, P : Port> {
    fun convert(command: C): P
}