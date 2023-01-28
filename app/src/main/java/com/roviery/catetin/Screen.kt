package com.roviery.catetin

sealed class Screen(val route: String){
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
}
