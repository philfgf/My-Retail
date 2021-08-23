package com.target.myRetail.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
data class ProductData(
    @Id
    val productId: Int,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("currentPrice")
    val currentPrice: List<CurrentPrice?>?
) {
    data class CurrentPrice(
        @JsonProperty("value")
        val value: String?,
        @JsonProperty("currencyCode")
        val currencyCode: String?
    )
}
