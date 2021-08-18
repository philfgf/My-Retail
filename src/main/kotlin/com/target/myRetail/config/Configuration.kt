package com.target.myRetail.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class Configuration(
    @Value("\${config.myRetailUrl}") val myRetailUrl: String
)
