package com.example.memorygame.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorygame.R

@Composable
fun AlertDialogSample(
    navController: NavController,
    openDialog: MutableState<Boolean>,
    time: MutableState<Int>,
    movement: Int,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {

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
                        color = MaterialTheme.colors.surface
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
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Time: ${60 - time.value} seconds",
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Movements: $movement",
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    navController.popBackStack("menu", false)
                    onDismissButton()
                }) {
                Text(stringResource(id = R.string.back),
                    color = MaterialTheme.colors.surface)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                    onConfirmButton()
                }) {
                Text(stringResource(id = R.string.play),
                    color = MaterialTheme.colors.surface)
            }
        },
    )
}