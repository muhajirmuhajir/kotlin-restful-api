package id.muhajir.kotlinrestfulapi.service.impl

import id.muhajir.kotlinrestfulapi.entity.Product
import id.muhajir.kotlinrestfulapi.error.NotFoundException
import id.muhajir.kotlinrestfulapi.model.CreateProductRequest
import id.muhajir.kotlinrestfulapi.model.ListProductRequest
import id.muhajir.kotlinrestfulapi.model.ProductResponse
import id.muhajir.kotlinrestfulapi.model.UpdateProductRequest
import id.muhajir.kotlinrestfulapi.repository.ProductRepository
import id.muhajir.kotlinrestfulapi.service.ProductService
import id.muhajir.kotlinrestfulapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository,
        val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
                id = createProductRequest.id!!,
                name = createProductRequest.name!!,
                quantity = createProductRequest.quantity!!,
                price = createProductRequest.price!!,
                createdAt = Date(),
                updatedAt = null
        )
        productRepository.save(product)

        return convertProductToResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException()

        return convertProductToResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException()

        product.apply {
            name = updateProductRequest.name!!
            quantity = updateProductRequest.quantity!!
            price = updateProductRequest.price!!
            updatedAt = Date()
        }
        productRepository.save(product)

        return convertProductToResponse(product)
    }

    override fun delete(id: String): String {
        val product = productRepository.findByIdOrNull(id) ?: throw NotFoundException()

        productRepository.delete(product)

        return "Product Deleted"
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToResponse(it) }
    }

    private fun convertProductToResponse(product: Product): ProductResponse {
        return ProductResponse(
                id = product.id,
                name = product.name,
                quantity = product.quantity,
                price = product.price,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt
        )
    }
}