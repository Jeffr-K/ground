package com.meshcraft.groundprofile.interfaces.controller

import com.meshcraft.groundprofile.core.command.ProfileCommandHandler
import com.meshcraft.groundprofile.interfaces.dto.ProfileEditRequestDto
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profiles")
@Validated
class ProfileController(
    private val profileCommandHandler: ProfileCommandHandler
) {
    @GetMapping
    fun profiles(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) {
        // @Valid exception: MethodArgumentNotValidException
    }

    @GetMapping("/me")
    fun profile() {
        // @Valid exception: ConstraintViolationException
    }

    @PatchMapping("/{memberId}")
    fun editProfile(
        @PathVariable @Valid memberId: Long, // JWT 로 member 뜯어내고 멤버 Id 가 같은 profile 은 1:1 관계이니 그걸로 나중에 마이그레이션 하면 됌.
        @RequestBody @Valid profile: ProfileEditRequestDto
    ) {
        profileCommandHandler.editProfile(memberId, profile)
    }

}