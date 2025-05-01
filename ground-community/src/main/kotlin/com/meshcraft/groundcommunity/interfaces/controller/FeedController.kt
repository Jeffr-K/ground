package com.meshcraft.groundcommunity.interfaces.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class FeedController(

) {
    @GetMapping
    fun feeds() {}

    @GetMapping
    fun feed() {}

    @PostMapping
    fun createFeed() {}

    @PutMapping
    fun editFeed() {}

    @DeleteMapping
    fun deleteFeed() {}
}