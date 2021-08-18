package com.target.myRetail.controller

import com.target.myRetail.config.Configuration
import com.target.myRetail.model.ProductData
import com.target.myRetail.service.ProjectRepository
import com.target.myRetail.util.getMyRetailData
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException

@RestController
class ProductsController(private val configuration: Configuration) {

    @Autowired
    lateinit var repository: ProjectRepository

    val logger: Logger = LogManager.getLogger(ProductsController::class.java)

    @RequestMapping("/products/{id}", method = [RequestMethod.GET])
    fun getProductController(
        @RequestParam("product_id") productId: String
    ): ResponseEntity<ProductData> {
        try {
            val myRetailData = getMyRetailData(productId, configuration)
            val productData = repository.findById(myRetailData.product.availableToPromiseNetwork.productId.toInt())
            val filteredProductData = productData.filter { it.productId == productId.toInt() }
            val responseProductData = ProductData(
                productId = productId.toInt(),
                name = filteredProductData[0].name,
                price = listOf(
                    ProductData.CurrentPrice(
                        value = filteredProductData[0].price[0].value,
                        currencyCode = filteredProductData[0].price[0].currencyCode
                    )
                )
            )
            return ResponseEntity(responseProductData, HttpStatus.OK)
        } catch (e: WebClientResponseException) {
            logger.error(
                "ProductsController: An error has occurred, ${e.message}, Response Code: ${e.statusCode}"
            )
            throw ResponseStatusException(e.statusCode, e.message)
        }
    }
}
