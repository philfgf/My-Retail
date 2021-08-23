package com.target.myRetail.controller

import com.target.myRetail.config.Configuration
import com.target.myRetail.model.ProductData
import com.target.myRetail.service.ProductRepository
import com.target.myRetail.util.getMyRetailData
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException

@RestController
class ProductsController(private val configuration: Configuration) {

    @Autowired
    lateinit var repository: ProductRepository

    val logger: Logger = LogManager.getLogger(ProductsController::class.java)

    @RequestMapping("/products/{productId}", method = [RequestMethod.GET])
    fun getProduct(
        @PathVariable("productId") productId: Int
    ): ResponseEntity<ProductData> {
        try {
            val myRetailData = getMyRetailData(productId, configuration)
            val productData = repository.findByProductId(productId).get()
            val responseProductData = ProductData(
                productId = productId,
                name = myRetailData.product.item.productDescription.title,
                currentPrice = listOf(
                    ProductData.CurrentPrice(
                        value = productData.currentPrice!![0]!!.value,
                        currencyCode = productData.currentPrice!![0]!!.currencyCode
                    )
                )
            )
            return ResponseEntity(responseProductData, HttpStatus.OK)
        } catch (e: WebClientResponseException) {
            logger.error(
                "getProduct: An error has occurred, ${e.message}, Response Code: ${e.statusCode}"
            )
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }

    @RequestMapping("/products/{productId}", method = [RequestMethod.PUT])
    fun putProduct(
        @PathVariable("productId") productId: Int,
        @RequestBody updatedProductData: ProductData
    ): ResponseEntity<ProductData> {
        try {
            val productData = repository.findByProductId(productId).get()
            val responseProductData = repository.save(
                ProductData(
                    productId = productData.productId,
                    name = productData.name,
                    currentPrice = listOf(
                        ProductData.CurrentPrice(
                            value = updatedProductData.currentPrice!![0]!!.value,
                            currencyCode = productData.currentPrice!![0]!!.currencyCode
                        )
                    )
                )
            )
            return ResponseEntity(responseProductData, HttpStatus.OK)
        } catch (e: WebClientResponseException) {
            logger.error(
                "putProduct: An error has occurred, ${e.message}, Response Code: ${e.statusCode}"
            )
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }
}
