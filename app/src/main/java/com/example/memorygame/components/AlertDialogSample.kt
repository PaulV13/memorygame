package com.example.memorygame.components


import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController

@Composable
fun AlertDialogSample(
    navController: NavController,
    openDialog: MutableState<Boolean>,
    time: MutableState<Int>,
    onConfirmButton: () -> Unit,
    onDismissButton: () -> Unit
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text =
                    if(time.value == 0) "Se acabo el tiempo!!!"
                    else "Excelente acerto todas en ${60 - time.value} segundos!!!")
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
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        navController.navigate("menu")
                        onDismissButton()
                    }) {
                    Text("Volver al menu")
                }
            }
        )
    }
}