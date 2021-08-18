package com.target.myRetail.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @RequestMapping("/health")
    fun health() = "server ok"
}
