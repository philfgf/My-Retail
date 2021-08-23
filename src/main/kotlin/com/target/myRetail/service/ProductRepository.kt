package com.target.myRetail.service

import com.target.myRetail.model.ProductData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductRepository : MongoRepository<ProductData, Int> {
    fun findByProductId(id: Int): Optional<ProductData>
}
