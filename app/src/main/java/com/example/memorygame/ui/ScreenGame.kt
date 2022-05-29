package com.example.memorygame.ui


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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.ui.components.AlertDialogSample
import com.example.memorygame.ui.components.CustomCard
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
    timerStarting: MutableState<Boolean>,
    openDialog: MutableState<Boolean>,
    indexSelected1: MutableState<Int>,
    indexSelected2: MutableState<Int>,
    onSelected1: (index: Int) -> Unit,
    onSelected2: (index: Int) -> Unit,
    onResetGame: () -> Unit,
    onRandomList: () -> Unit,
    onBackToMenu: () -> Unit,
    onGameOver: () -> Unit,
    onStartTimer: () -> Unit
) {

    LaunchedEffect(
        key1 = time.value,
        key2 = timerStarting.value) {
        delay(1000)
        if (time.value > 0 && !openDialog.value && timerStarting.value) {
            onStartTimer()
        } else if (time.value == 0) {
            onGameOver()
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(id = R.string.pairs),
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "$parCount",
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = stringResource(id = R.string.movements),
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "$movement",
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
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
                        columns = columns,
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Text(
                text =
                if (time.value == 60) "Time: "
                else if (time.value >= 10) "Time: "
                else "Time: ",
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = if (time.value == 60) "01:00"
                else if (time.value >= 10) "00:${time.value}"
                else "00:0${time.value}",
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h6
            )
        }
        if (openDialog.value) {
            AlertDialogSample(
                navController = navController,
                openDialog = openDialog,
                time = time,
                movement = movement,
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
        }) { Text(stringResource(id = R.string.back),
            color = MaterialTheme.colors.surface) }
        Spacer(modifier = Modifier.height(30.dp))
    }

}
