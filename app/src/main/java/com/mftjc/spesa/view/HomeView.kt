package com.mftjc.spesa.view

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mftjc.spesa.model.Product
import com.mftjc.spesa.navigation.Screen
import com.mftjc.spesa.ui.theme.Green
import com.mftjc.spesa.ui.theme.LightGreen
import com.mftjc.spesa.ui.theme.LightWhite
import com.mftjc.spesa.viewmodel.ProductVm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: ProductVm,
    navHostController: NavHostController,
    context: Context
){


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                Text(text = "Spesa")
                },
                actions = {
                    IconButton(onClick = {
                        vm.deleteAllProducts()
                        Toast.makeText(context, "Tutti i prodotti sono stati eliminati", LENGTH_SHORT).show()
                    }){
                        Icon(Icons.Filled.Delete, contentDescription = "Delete all products")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Green),
            )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(LightWhite),
            contentAlignment = Alignment.TopCenter
        ){
            ShowListProducts(navHostController = navHostController, vm = vm, context = context)
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                FloatingActionButton(
                    onClick = { navHostController.navigate(Screen.AddOrUpdateProductScreen.passId(0))},
                    containerColor = Green
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Show AddProductScreen")
                }
            }

        }
    }
}

@Composable
private fun ShowListProducts(
    navHostController: NavHostController,
    vm: ProductVm,
    context: Context
){
    val products by vm.getAllProducts().collectAsState(initial = emptyList())

    //if there is no product
    if (products.isEmpty()){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "La lista è vuota.",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                color = Color.Gray
            )
        }
    }
    else {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(products){ product ->
                ProductCard(product = product, navHostController, vm, context)
            }

        }   
    }
}

@Composable
private fun ProductCard(
    product: Product,
    navHostController: NavHostController,
    vm: ProductVm,
    context: Context
){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(10.dp)
            .shadow(5.dp, shape = RoundedCornerShape(5.dp)),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(LightGreen)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    text = "${product.name}: ${product.quantity}"
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ){
                    IconButton(onClick = {
                        navHostController.navigate(Screen.AddOrUpdateProductScreen.passId(product.id))
                    }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Show UpdateScreenView")
                    }
                    IconButton(onClick = {
                        vm.deleteProduct(product)
                        Toast.makeText(context, "Prodotto Eliminato", LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Show DeleteScreenView")
                    }
                }

            }
        }
    }
}
