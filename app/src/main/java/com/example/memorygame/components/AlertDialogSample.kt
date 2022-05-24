package com.example.memorygame.components


import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.model.Card

@Composable
fun AlertDialogSample(
    navController: NavController,
    openDialog: MutableState<Boolean>,
    time: MutableState<Int>,
    cards:  MutableList<Card>,
    parCount: Int,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(
                text =
                if (time.value == 0) "Time's up! Try again!"
                else "Congratulations, you got them all right!!!!"
            )
        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    navController.popBackStack("menu", false)
                    onDismissButton()
                }) {
                Text(stringResource(id = R.string.back))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                    onConfirmButton()
                }) {
                Text(stringResource(id = R.string.play))
            }
        },
    )
}