package com.example.memorygame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memorygame.screens.ScreenGame
import com.example.memorygame.screens.ScreenMenu
import com.example.memorygame.ui.theme.MemorygameTheme
import com.example.memorygame.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            MemorygameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: GameViewModel by viewModels()

                    NavHost(navController = navController, startDestination = "menu") {
                        composable("menu") {
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
                        composable("game"){
                                ScreenGame(navController = navController,
                                    cards =  viewModel.cards.value,
                                    columns = viewModel.columns,
                                    parCount = viewModel.parCount,
                                    movement = viewModel.movement,
                                    clicks = viewModel.clicks,
                                    time = viewModel.time,
                                    openDialog = viewModel.openDialog,
                                    indexSelected1 = viewModel.choiceIndex1,
                                    indexSelected2 = viewModel.choiceIndex2,
                                    list = viewModel.list,
                                    onSelected1 = { index -> viewModel.getIndex1(index) },
                                    onSelected2 = { index -> viewModel.getIndex2(index) },
                                    onResetGame = { viewModel.resetGame() },
                                    onBackToMenu = { viewModel.resetGame() },
                                    onRandomList = { viewModel.getRandomList() },
                                    onGameOver = { viewModel.showAlert() }
                                )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MemorygameTheme {

    }
}