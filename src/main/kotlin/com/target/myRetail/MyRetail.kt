package com.target.myRetail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MyRetail

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    SpringApplication.run(MyRetail::class.java, *args)
}
