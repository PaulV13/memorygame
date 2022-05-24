package com.example.memorygame.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(400)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(400)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(400)
    )

    val modifier =
        if (columns == 5) Modifier
            .padding(4.dp)
            .width(50.dp)
            .height(70.dp)
        else Modifier
            .padding(4.dp)
            .width(50.dp)
            .height(75.dp)

    if (list.value.contains(card.id)) {
        Card(
            elevation = 4.dp,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.White,
            modifier = modifier
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }) {
            Image(
                painter = painterResource(id =card.image),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationY = rotation
                    }
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    } else {
        Card(
            elevation = 4.dp,
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.White,
            modifier = modifier
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable {
                    if (indexSelected1.value == index) {
                        return@clickable
                    }
                    if ((indexSelected1.value == -1 || indexSelected2.value == -1)) {
                        rotated = !rotated
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
                painter = painterResource(
                    id =
                    if (indexSelected1.value == index || indexSelected2.value == index)
                        card.image
                    else
                        card.imageBack
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationY = rotation
                    }
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }

}
