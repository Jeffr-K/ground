package com.meshcraft.groundprofile.infrastructure.funcs

fun generateRandomNickname(): String {
    val adjectives = listOf(
        "Adventurous", "Brave", "Clever", "Daring", "Eager",
        "Fearless", "Generous", "Honest", "Joyful", "Kind"
    )
    val nouns = listOf(
        "Explorer", "Warrior", "Sage", "Guardian", "Hero",
        "Seeker", "Dreamer", "Visionary", "Champion", "Legend"
    )

    val randomAdjective = adjectives.random()
    val randomNoun = nouns.random()

    return "$randomAdjective $randomNoun"
}