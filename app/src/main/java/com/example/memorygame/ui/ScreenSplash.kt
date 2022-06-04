package com.example.memorygame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.memorygame.R
import com.example.memorygame.navigation.AppScreen
import kotlinx.coroutines.delay

@Composable
fun ScreenSplash(navController: NavController){

    LaunchedEffect(key1 = true){
        delay(1000)
        navController.popBackStack()
        navController.navigate(AppScreen.ScreenMenu.route)
    }

    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "")
    }
}



@Preview(showBackground = true)
@Composable
fun ScreenSplashPreview(){
    Splash()
}