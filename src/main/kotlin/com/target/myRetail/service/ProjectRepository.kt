package com.target.myRetail.service

import com.target.myRetail.model.ProductData
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : MongoRepository<ProductData, Int> {
    fun findById(id: String): ProductData
}
