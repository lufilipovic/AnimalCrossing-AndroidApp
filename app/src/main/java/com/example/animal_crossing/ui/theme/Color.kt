package com.example.animal_crossing.ui.theme

import androidx.compose.ui.graphics.Color

//val Purple200 = Color(0, 0, 139)
//val Purple500 = Color(116,224,170)
//val Purple700 = Color(219,191,158)
//val Teal200 = Color(0xFF03DAC5)

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color
){
    object Night: ThemeColors(
        background = Color(0XFF000000),
        surface = Color(0XFF000000),
        primary = Color(219,191,158),
        text = Color(0XFFFFFFFFF)
    )
    object Day: ThemeColors(
        background = Color(0XFFFFFFFFF),
        surface = Color(0XFFFFFFFFF),
        primary = Color(116,224,170),
        text = Color(0XFF000000)

    )
}