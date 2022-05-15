package com.example.memorygame.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.memorygame.model.Card


@Composable
fun CustomCard(
    card: Card,
    index: Int,
    clicks: MutableState<Int>,
    indexSelected1: MutableState<Int>,
    indexSelected2: MutableState<Int>,
    onSelected1: (index: Int) -> Unit,
    onSelected2: (index: Int) -> Unit,
    list: MutableState<MutableList<Int>>,
    columns: Int,
){

    val modifier =
        if(columns == 5) Modifier
            .padding(4.dp)
            .width(50.dp)
            .height(70.dp)
        else Modifier
            .padding(4.dp)
            .width(50.dp)
            .height(75.dp)

    if(list.value.contains(card.id)){
        Card(
            backgroundColor = Color.Transparent,
            modifier = modifier){}
    }else {
        Card(
            elevation = 4.dp,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.White,
            modifier = modifier
                .clickable {
                    if (indexSelected1.value == index) {
                        return@clickable
                    }
                    if ((indexSelected1.value == -1 || indexSelected2.value == -1)) {
                        clicks.value++
                        if (clicks.value == 1) {
                            onSelected1(index)
                        } else if (clicks.value == 2) {
                            onSelected2(index)
                            clicks.value = 0
                        }
                    }
                }) {
                    Image(
                        painter = painterResource(id =
                        if(indexSelected1.value == index || indexSelected2.value == index)
                            card.image
                        else
                            card.imageBack),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(shape = RoundedCornerShape(4.dp))
                    )
        }
    }

}