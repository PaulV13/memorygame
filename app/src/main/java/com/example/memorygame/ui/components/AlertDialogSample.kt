package com.example.memorygame.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.ui.scaledSp

@Composable
fun AlertDialogSample(
    navController: NavController,
    openDialog: MutableState<Boolean>,
    time: MutableState<Int>,
    movement: Int,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {
    var sizeText = 16
    var sizeTextButtons = 12

    AlertDialog(
        onDismissRequest = {},
        title = {
            if(time.value == 0){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Time's up! Try again!",
                        fontSize = sizeText.scaledSp(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }else {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Congratulations, you have won!",
                        fontSize = sizeText.scaledSp(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Time: ${60 - time.value} seconds",
                        fontSize = sizeText.scaledSp(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Movements: $movement",
                        fontSize = sizeText.scaledSp(),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        buttons = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center){
                Button(
                    onClick = {
                        openDialog.value = false
                        navController.popBackStack("menu", false)
                        onDismissButton()
                    }) {
                    Text(stringResource(id = R.string.back),
                        fontSize = sizeTextButtons.scaledSp(),
                        color = MaterialTheme.colors.surface)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = {
                        openDialog.value = false
                        onConfirmButton()
                    }) {
                    Text(stringResource(id = R.string.play),
                        fontSize = sizeTextButtons.scaledSp(),
                        color = MaterialTheme.colors.surface)
                }
            }
        },
    )
}