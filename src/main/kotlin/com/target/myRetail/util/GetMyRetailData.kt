package com.target.myRetail.util

import com.target.myRetail.MyRetail
import com.target.myRetail.config.Configuration
import com.target.myRetail.model.MyRetailData
import org.apache.logging.log4j.LogManager
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException

fun getMyRetailData(productId: Int, configuration: Configuration): MyRetailData {
    val logger = LogManager.getLogger(MyRetail::class.java)
    val webClient = WebClient.create(configuration.myRetailUrl)
    return try {
        val result = webClient.get()
            .uri("$productId?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews," +
                            "rating_and_review_statistics,question_answer_statistics&key=candidate")
            .retrieve()
            .bodyToMono(MyRetailData::class.java)
        result.block()!!
    } catch (e: WebClientResponseException) {
        logger.error("getMyRetailData: An error has occurred, ${e.message}, Response Code: ${e.statusCode}")
        throw ResponseStatusException(e.statusCode, e.message)
    }
}
