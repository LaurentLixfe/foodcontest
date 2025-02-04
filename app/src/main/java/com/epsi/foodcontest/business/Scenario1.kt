package com.epsi.foodcontest.business

object Scenario1 {

    const val TIME_BETWEEN_PLATE = 2000L
    const val NB_ROUND = 4
    private const val NB_FOOD = 3

    fun shuffleList(): List<Int> {
        val allowedWeight = listOf(50, 300, 100, 550, 1500, 50, 900)
        return allowedWeight.shuffled().take(NB_FOOD)
    }

    fun isInvalid(foods: List<Int>) =
        foods.any { food -> food > 600 }

}
