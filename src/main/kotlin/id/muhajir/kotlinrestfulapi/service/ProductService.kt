package id.muhajir.kotlinrestfulapi.service

import id.muhajir.kotlinrestfulapi.model.CreateProductRequest
import id.muhajir.kotlinrestfulapi.model.ListProductRequest
import id.muhajir.kotlinrestfulapi.model.ProductResponse
import id.muhajir.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest ): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun delete(id: String): String
    fun list(listProductRequest: ListProductRequest ): List<ProductResponse>
}