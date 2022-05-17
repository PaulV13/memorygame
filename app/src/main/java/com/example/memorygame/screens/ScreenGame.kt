package com.example.memorygame.screens


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.components.AlertDialogSample
import com.example.memorygame.components.CustomCard
import com.example.memorygame.model.Card
import kotlinx.coroutines.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenGame(
    navController: NavController,
    cards: MutableList<Card>,
    columns: Int,
    parCount: Int,
    movement: Int,
    clicks: MutableState<Int>,
    time: MutableState<Int>,
    openDialog: MutableState<Boolean>,
    indexSelected1: MutableState<Int>,
    indexSelected2: MutableState<Int>,
    list: MutableState<MutableList<Int>>,
    onSelected1: (index: Int) -> Unit,
    onSelected2: (index: Int) -> Unit,
    onResetGame: () -> Unit,
    onRandomList: () -> Unit,
    onBackToMenu: () -> Unit,
    onGameOver: () -> Unit
) {


    LaunchedEffect(key1 = time.value) {
        delay(1000)
        if (time.value > 0 && !openDialog.value) {
            time.value--
        } else if (time.value == 0) {
            onGameOver()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(id = R.string.title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyVerticalGrid(
                cells = GridCells.Fixed(columns),
                contentPadding = PaddingValues(
                    start = 4.dp,
                    top = 0.dp,
                    end = 4.dp,
                    bottom = 0.dp
                ),
                content = {
                    items(cards.size) { index ->
                        CustomCard(
                            card = cards[index],
                            index = index,
                            clicks = clicks,
                            indexSelected1 = indexSelected1,
                            indexSelected2 = indexSelected2,
                            onSelected1 = onSelected1,
                            onSelected2 = onSelected2,
                            list = list,
                            columns = columns
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Remaining pairs: $parCount")
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text =
                if (time.value == 60) "Time: 1:00"
                else if (time.value >= 10) "Time: 00:${time.value}"
                else "Time: 00:0${time.value}"
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Movements: $movement")
            if(openDialog.value){
                AlertDialogSample(
                    navController = navController,
                    openDialog = openDialog,
                    time = time,
                    onConfirmButton = {
                        onResetGame()
                        onRandomList()
                    },
                    onDismissButton = {
                        onBackToMenu()
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            BackHandler {
                navController.popBackStack("menu", false)
                onBackToMenu()
            }
            Button(onClick = {
                if (indexSelected1.value == -1 || indexSelected2.value == -1) {
                    navController.popBackStack("menu", false)
                    onBackToMenu()
                }
            }) { Text(stringResource(id = R.string.back)) }
        }
    }
}
