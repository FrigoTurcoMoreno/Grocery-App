package com.mftjc.spesa.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mftjc.spesa.db.ProductDb
import com.mftjc.spesa.model.Product
import com.mftjc.spesa.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductVm(context: Context) : ViewModel() {

    private val db = ProductDb.getInstance(context)

    private val dao = db.productDao()

    private val repository = ProductRepository(dao)

    fun getAllProducts(): Flow<List<Product>> {
        return repository.products
    }

    fun getProduct(id: Int): Flow<Product>{
        return repository.getProduct(id)
    }

    fun insertOrUpdateProduct(product: Product){
        viewModelScope.launch {
            repository.insertOrUpdateProduct(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            repository.deleteProduct(product)
        }
    }


    fun deleteAllProducts(){
        viewModelScope.launch {
            repository.deleteAllProducts()
        }
    }



}