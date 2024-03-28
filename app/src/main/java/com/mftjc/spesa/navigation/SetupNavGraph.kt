package com.mftjc.spesa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mftjc.spesa.view.AddProductView
import com.mftjc.spesa.view.HomeView
import com.mftjc.spesa.view.UpdateProductView
import com.mftjc.spesa.viewmodel.ProductVm

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    productVm: ProductVm
){
    NavHost(
        navHostController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(
            route = Screen.HomeScreen.route
        ){
            HomeView(vm = productVm, navHostController)
        }
        composable(
            route = Screen.AddProductScreen.route
        ){
            AddProductView(vm = productVm, navHostController)
        }
        composable(
            route = Screen.UpdateProductScreen.route,
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ){
            UpdateProductView(vm = productVm, navHostController = navHostController, id = it.arguments!!.getInt("id"))
        }
    }
}