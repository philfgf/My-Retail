package com.target.myRetail.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MyRetailData(
    @JsonProperty("product")
    val product: Product
) {
    data class Product(
        @JsonProperty("available_to_promise_network")
        val availableToPromiseNetwork: AvailableToPromiseNetwork,
        @JsonProperty("item")
        val item: Item
    ) {
        data class AvailableToPromiseNetwork(
            @JsonProperty("product_id")
            val productId: String,
        )
        data class Item(
            @JsonProperty("product_description")
            val productDescription: ProductDescription,
        ) {
            data class ProductDescription(
                @JsonProperty("title")
                val title: String
            )
        }
    }
}
