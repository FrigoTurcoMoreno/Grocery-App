package com.mftjc.spesa.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mftjc.spesa.db.ProductDb
import com.mftjc.spesa.model.Product
import kotlinx.coroutines.launch

class ProductVm(context: Context) : ViewModel() {

    private val db = ProductDb.getInstance(context)

    private val dao = db.productDao()

    val products = dao.getAllProducts()

    suspend fun getProduct(id: Int): Product{
        return dao.getProduct(id)
    }

    fun insertOrUpdateProduct(product: Product){
        viewModelScope.launch {
            dao.insertOrUpdateProduct(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            dao.deleteProduct(product)
        }
    }

    fun deleteAllProducts(){
        viewModelScope.launch {
            dao.deleteAllProducts()
        }
    }
}