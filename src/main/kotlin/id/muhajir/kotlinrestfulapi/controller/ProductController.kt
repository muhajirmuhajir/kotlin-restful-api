package id.muhajir.kotlinrestfulapi.controller

import id.muhajir.kotlinrestfulapi.model.*
import id.muhajir.kotlinrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
            value = ["/api/products"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(body)

        return convertProductResponseToWebResponse(productResponse)
    }

    @GetMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)

        return convertProductResponseToWebResponse(productResponse)
    }

    @PutMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("idProduct") id: String, @RequestBody updateProductRequest: UpdateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.update(id, updateProductRequest)
        return convertProductResponseToWebResponse(productResponse)
    }

    @DeleteMapping(
            value = ["/api/products/{idProduct}"],
            produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String> {
        val response = productService.delete(id)

        return WebResponse(
                code = 200,
                status = "OK",
                data = response
        )
    }

    @GetMapping(
            value = ["/api/products"],
            produces = ["application/json"]
    )
    fun listProduct(@RequestParam(value = "size", defaultValue = "10") size: Int, @RequestParam(value = "page", defaultValue = "0") page: Int): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(page, size)
        val response = productService.list(request)
        return WebResponse(
                code = 200,
                status = "OK",
                data = response
        )
    }

    private fun convertProductResponseToWebResponse(productResponse: ProductResponse): WebResponse<ProductResponse> {
        return WebResponse(
                code = 200,
                status = "OK",
                data = productResponse
        )
    }
}