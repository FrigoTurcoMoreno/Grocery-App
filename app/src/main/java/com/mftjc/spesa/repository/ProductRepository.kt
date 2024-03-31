package com.mftjc.spesa.repository

import com.mftjc.spesa.dao.ProductDao
import com.mftjc.spesa.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductDao){

    val products = dao.getAllProducts()

    fun getProduct(id: Int): Flow<Product> {
        return dao.getProduct(id)
    }

    suspend fun insertOrUpdateProduct(product: Product){
        dao.insertOrUpdateProduct(product)
    }

    suspend fun deleteProduct(product: Product){
        dao.deleteProduct(product)
    }

    suspend fun deleteAllProducts(){
        dao.deleteAllProducts()
    }
}