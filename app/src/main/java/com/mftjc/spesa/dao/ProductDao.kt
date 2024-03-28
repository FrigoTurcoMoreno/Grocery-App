package com.mftjc.spesa.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mftjc.spesa.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>


    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProduct(id: Int): Product


    @Upsert
    suspend fun insertOrUpdateProduct(product: Product)


    @Delete
    suspend fun deleteProduct(product: Product)


    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()
}