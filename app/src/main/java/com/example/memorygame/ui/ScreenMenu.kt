package com.example.memorygame.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorygame.R

@Composable
fun ScreenMenu(
    navController: NavController,
    onClicked: (number: Int, countPar: Int) -> Unit
){
    var sizeTextTitle = 32
    var sizeTextButtons = 12

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "MEMORY GAME",
                fontSize = sizeTextTitle.scaledSp(),
                color = MaterialTheme.colors.surface,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = {
                onClicked(4, 8)
                navController.navigate("game") },
                modifier = Modifier.width(100.dp)) {
                    Text(stringResource(id = R.string.easy),
                        fontSize = sizeTextButtons.scaledSp(),
                        color = MaterialTheme.colors.surface)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                onClicked(4, 12)
                navController.navigate("game") },
                modifier = Modifier.width(100.dp)) {
                Text(stringResource(id = R.string.normal),
                    fontSize = sizeTextButtons.scaledSp(),
                    color = MaterialTheme.colors.surface)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                onClicked(5, 15)
                navController.navigate("game") },
                modifier = Modifier.width(100.dp)) {
                Text(stringResource(id = R.string.hard),
                    fontSize = sizeTextButtons.scaledSp(),
                    color = MaterialTheme.colors.surface)
            }
        }
    }
}

@Composable
fun Int.scaledSp(): TextUnit {
    val value: Int = this
    return with(LocalDensity.current) {
        val fontScale = this.fontScale
        val textSize =  value / fontScale
        textSize.sp
    }
}