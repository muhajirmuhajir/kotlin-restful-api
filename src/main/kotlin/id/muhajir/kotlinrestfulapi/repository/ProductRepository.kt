package id.muhajir.kotlinrestfulapi.repository

import id.muhajir.kotlinrestfulapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String>{

}