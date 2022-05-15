package com.example.memorygame.model

import androidx.annotation.DrawableRes
import com.example.memorygame.R
import java.io.Serializable

data class Card(
    val id: Int,
    @DrawableRes
    val image: Int,
    @DrawableRes
    val imageBack: Int = R.drawable.cardback
): Serializable