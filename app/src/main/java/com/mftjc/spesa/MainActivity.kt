package com.mftjc.spesa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mftjc.spesa.navigation.SetupNavGraph
import com.mftjc.spesa.viewmodel.ProductVm

class MainActivity : ComponentActivity() {

    private lateinit var vm: ProductVm
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ProductVm(this)

        setContent {
            navHostController = rememberNavController()
            SetupNavGraph(navHostController = navHostController, productVm = vm)

        }
    }
}




