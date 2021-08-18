package com.target.myRetail.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductData(
    @JsonProperty("location_id")
    val productId: Int,
    @JsonProperty("location_id")
    val name: String,
    @JsonProperty("location_id")
    val price: List<CurrentPrice>
) {
    data class CurrentPrice(
        @JsonProperty("location_id")
        val value: String,
        @JsonProperty("location_id")
        val currencyCode: String
    )
}
