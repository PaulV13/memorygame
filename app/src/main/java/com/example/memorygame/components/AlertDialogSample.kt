package com.example.memorygame.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
        title = null,
        text = {
            Column{
                Text(
                    text =
                    if (time.value == 0) "Se acabo el tiempo!!!"
                    else "Felicitaciones!!!")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Acertaste ${(cards.size / 2) - parCount} parejas en ${60 - time.value} segundos!!!")
            }

        },
        dismissButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                    navController.popBackStack("menu", false)
                    onDismissButton()
                }) {
                Text("Volver al menu")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                    onConfirmButton()
                }) {
                Text("Volver a jugar")
            }
        },
    )
}