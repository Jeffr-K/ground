package com.meshcraft.groundprofile.infrastructure.funcs

fun String.validateBannedWords(words: List<String>): Boolean {
    val regex = Regex("\\b(${words.joinToString("|")})\\b", RegexOption.IGNORE_CASE)
    return this.contains(regex)
}