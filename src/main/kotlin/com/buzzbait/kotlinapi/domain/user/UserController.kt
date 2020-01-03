package com.buzzbait.kotlinapi.domain.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.collections.HashMap

@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/v1/userlist")
    fun userList() :ResponseEntity<HashMap<String,Any>> {
        var resultMap : HashMap<String,Any> = HashMap();
        resultMap.put("code",10);
        resultMap.put("value","10");

        return ResponseEntity(resultMap, HttpStatus.OK)
    }
}