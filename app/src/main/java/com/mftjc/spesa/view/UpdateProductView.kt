package com.mftjc.spesa.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavHostController
import com.mftjc.spesa.model.Product
import com.mftjc.spesa.viewmodel.ProductVm
import kotlinx.coroutines.Dispatchers

@Composable
fun UpdateProductView(vm: ProductVm, navHostController: NavHostController, id: Int){

    var product by remember { mutableStateOf(Product()) }
    var name by remember { mutableStateOf(product.name) }
    var quantity by remember { mutableStateOf(product.quantity) }

    LaunchedEffect(Dispatchers.IO){
        product = vm.getProduct(id)
        name = product.name
        quantity = product.quantity
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        Arrangement.SpaceAround,
        Alignment.CenterHorizontally
    ) {
        Box(){
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                placeholder = {
                    Text(text = "Nome Prodotto")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
        }
        Box(){
            TextField(
                value = quantity,
                onValueChange = {
                    quantity = it
                },
                placeholder = {
                    Text(text = "Quantità")
                },
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
            )
        }
        Button(
            onClick = {
                product.name = name
                product.quantity = quantity
                vm.insertOrUpdateProduct(product)
                navHostController.popBackStack()
            },
            enabled = name.isNotBlank() && quantity.isNotBlank()
        ) {
            Text(text = "Modifica")
        }
    }
}