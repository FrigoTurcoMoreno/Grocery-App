package com.mftjc.spesa.navigation

sealed class Screen(val route: String) {

    object HomeScreen: Screen(route = "home_screen")

    object AddOrUpdateProductScreen: Screen(route = "add_or_update_product_screen/{id}"){
        fun passId(id: Int): String{
            return "add_or_update_product_screen/$id"
        }
    }
}