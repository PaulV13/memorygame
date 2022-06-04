package com.example.memorygame.navigation

sealed class AppScreen(val route: String) {
    object ScreenSplash: AppScreen("splash")
    object ScreenMenu: AppScreen("menu")
    object ScreenGame: AppScreen("game")
}