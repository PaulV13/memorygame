package com.example.memorygame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memorygame.ui.ScreenGame
import com.example.memorygame.ui.ScreenMenu
import com.example.memorygame.ui.ScreenSplash
import com.example.memorygame.viewmodel.GameViewModel

@Composable
fun AppNavigation(viewModel: GameViewModel){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.ScreenSplash.route
    ){
        composable(AppScreen.ScreenSplash.route) {
            ScreenSplash(navController = navController)
        }
        composable(AppScreen.ScreenMenu.route) {
            ScreenMenu(
                navController = navController,
                onClicked = { number, countPar ->
                    run {
                        viewModel.updateColumns(number)
                        viewModel.updateCountPar(countPar)
                        viewModel.updateCards()
                    }
                }
            )
        }
        composable(AppScreen.ScreenGame.route){
            ScreenGame(navController = navController,
                cards =  viewModel.cards.value,
                columns = viewModel.columns.value,
                parCount = viewModel.parCount.value,
                movement = viewModel.movement.value,
                clicks = viewModel.clicks,
                time = viewModel.time,
                timerStarting = viewModel.timerStarting,
                openDialog = viewModel.openDialog,
                indexSelected1 = viewModel.choiceIndex1,
                indexSelected2 = viewModel.choiceIndex2,
                onSelected1 = { index -> viewModel.getIndex1(index) },
                onSelected2 = { index -> viewModel.getIndex2(index) },
                onResetGame = { viewModel.resetGame() },
                onBackToMenu = { viewModel.resetGame() },
                onRandomList = { viewModel.getRandomList() },
                onGameOver = { viewModel.showAlert() },
                onStartTimer = { viewModel.startTimer()}
            )
        }
    }
}