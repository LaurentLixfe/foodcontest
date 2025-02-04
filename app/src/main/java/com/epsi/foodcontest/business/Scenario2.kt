package com.epsi.foodcontest.business

import com.epsi.foodcontest.models.Comestible
import com.epsi.foodcontest.utils.createListOfComestible

object Scenario2 {

    const val TIME_BETWEEN_PLATE = 2000L
    const val NB_ROUND = 4
    private const val NB_FOOD = 3

    fun shuffleList(): List<Comestible> {
        val allowedComestible = createListOfComestible()
        return allowedComestible.shuffled().take(NB_FOOD)
    }

    fun isInvalid(foods: List<Comestible>) =
        foods.any { food -> food.weight() > 600 } || foods.isEmpty()

}
