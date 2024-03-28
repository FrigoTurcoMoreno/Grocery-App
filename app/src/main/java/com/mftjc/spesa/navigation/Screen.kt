package com.mftjc.spesa.navigation

sealed class Screen(val route: String) {

    object HomeScreen: Screen(route = "home_screen")

    object AddProductScreen: Screen(route = "add_product_screen")

    object UpdateProductScreen: Screen(route = "update_product_screen/{id}"){
        fun passId(id: Int): String{
            return "update_product_screen/$id"
        }
    }
}