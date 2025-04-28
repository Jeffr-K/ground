package com.meshcraft.groundmember.interfaces

import com.meshcraft.groundmember.core.command.MemberRegisterCommand
import com.meshcraft.groundmember.core.command.MemberRegisterCommandHandler
import com.meshcraft.groundmember.core.entity.Member
import com.meshcraft.groundmember.interfaces.dto.MemberRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("users")
class MemberController(
    val memberRegisterCommandHandler: MemberRegisterCommandHandler
) {

    @PostMapping
    fun createUser(@RequestBody dto: MemberRequestDto): ResponseEntity<Long> {
        val newMemberId = this.memberRegisterCommandHandler.register(MemberRegisterCommand(
            username = dto.username,
            password = dto.password,
            email = dto.email,
            phone = dto.phone
        ))
        return ResponseEntity.ok(newMemberId)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun editUser(@PathVariable id: Long, @RequestBody member: Member): ResponseEntity<Member> {
        return ResponseEntity.ok(member)
    }

    @GetMapping
    fun getUsers(): ResponseEntity<List<Member>> {
        val members = listOf<Member>()
        return ResponseEntity.ok(members)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<Member> {
        val member = Member(
            username = "test",
            password = "test",
            email = "jeff.cofos@gmail.com",
            phone = "01031232244"
        )
        return ResponseEntity.ok(member)
    }
}