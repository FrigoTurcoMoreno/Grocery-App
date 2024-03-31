package com.mftjc.spesa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavHostController
import com.mftjc.spesa.model.Product
import com.mftjc.spesa.ui.theme.Green
import com.mftjc.spesa.ui.theme.LightGreen
import com.mftjc.spesa.ui.theme.LightWhite
import com.mftjc.spesa.viewmodel.ProductVm
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrUpdateProductView(vm: ProductVm, navHostController: NavHostController, id: Int){

    var product = vm.getProduct(id).collectAsState(initial = Product()).value
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("Inserisci Prodotto") }
    var nameButton by remember { mutableStateOf("Inserisci") }

    if (id != 0) {
        name = product.name
        quantity = product.quantity
        title = "Modifica Prodotto"
        nameButton = "Modifica"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back to HomeView")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Green)
            )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(LightWhite)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                Arrangement.SpaceAround,
                Alignment.CenterHorizontally
            ) {
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(
                            text = "Nome Prodotto"
                        )
                    },
                    keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Green,
                        unfocusedContainerColor = LightGreen
                    )
                )
                TextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it
                    },
                    label = {
                        Text(text = "Quantità")
                    },
                    keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Green,
                        unfocusedContainerColor = LightGreen
                    )
                )
                Button(
                    onClick = {
                        //if no product is found, it will be null
                        if (product != null){
                            product.name = name
                            product.quantity = quantity
                        }
                        else {
                            product = Product(name = name, quantity = quantity)
                        }
                        vm.insertOrUpdateProduct(product)
                        navHostController.popBackStack()
                    },
                    enabled = name.isNotBlank() && quantity.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        Green,
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(text = nameButton)
                }
            }
        }
    }
}