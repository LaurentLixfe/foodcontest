package com.epsi.foodcontest

import com.epsi.foodcontest.models.Comestible
import com.epsi.foodcontest.models.WomanHealth

data class MainState(
    val womanHealth: WomanHealth = WomanHealth.EATING,
    val plate : List<Comestible> = emptyList(),
    val womanHealthAtEnd: WomanHealth = WomanHealth.HAPPY,
    val isGameEnded : Boolean = false,
    val roundTime : Long
)