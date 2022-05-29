package com.example.memorygame.model

import androidx.annotation.DrawableRes
import com.example.memorygame.R
import java.io.Serializable

data class Card(
    val id: Int,
    val name: String,
    @DrawableRes
    val image: Int,
    @DrawableRes
    val imageBack: Int = R.drawable.cardback,
    var isVisible: Boolean = false
): Serializable