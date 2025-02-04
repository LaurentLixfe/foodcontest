package com.epsi.foodcontest

import com.epsi.foodcontest.models.WomanHealth

data class MainState(
    val womanHealth: WomanHealth = WomanHealth.EATING,
    val plate : List<Int> = emptyList(),
    val womanHealthAtEnd: WomanHealth = WomanHealth.HAPPY,
    val isGameEnded : Boolean = false,
)