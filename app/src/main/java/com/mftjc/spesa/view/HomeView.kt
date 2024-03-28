package com.mftjc.spesa.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mftjc.spesa.model.Product
import com.mftjc.spesa.navigation.Screen
import com.mftjc.spesa.viewmodel.ProductVm

@Composable
fun HomeView(
    vm: ProductVm,
    navHostController: NavHostController
){
    Column(modifier = Modifier
        .fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier
            .fillMaxHeight(0.8f)
        ) {
            ShowListProducts(navHostController, vm)
        }
        IconButton(
            onClick = {
                navHostController.navigate(Screen.AddProductScreen.route)
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Show AddProductScreen")
        }
    }
}

@Composable
private fun ShowListProducts(
    navHostController: NavHostController,
    vm: ProductVm
){
    val products by vm.products.collectAsState(initial = emptyList())

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(products){ product ->
            ProductCard(product = product, navHostController, vm)
        }

    }
}

@Composable
private fun ProductCard(
    product: Product,
    navHostController: NavHostController,
    vm: ProductVm
){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(75.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    text = "${product.name}: ${product.quantity}"
                )
            }
            Box(
                contentAlignment = Alignment.Center
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ){
                    IconButton(onClick = {
                        navHostController.navigate(Screen.UpdateProductScreen.passId(product.id))
                    }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Show UpdateScreenView")
                    }
                    IconButton(onClick = {
                        vm.deleteProduct(product)
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Show DeleteScreenView")
                    }
                }

            }
        }
    }
}
