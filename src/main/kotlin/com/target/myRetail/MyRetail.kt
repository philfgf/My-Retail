package com.target.myRetail

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class MyRetail

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    SpringApplication.run(MyRetail::class.java, *args)
}
