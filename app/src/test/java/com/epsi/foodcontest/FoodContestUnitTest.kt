package com.epsi.foodcontest

import com.epsi.foodcontest.business.Scenario1
import com.epsi.foodcontest.utils.removeHugeFoods
import org.junit.Test

import org.junit.Assert.*

class FoodContestUnitTest {
    @Test
    fun testScenario1() {
        val allRoundItems = mutableListOf<Int>()
        repeat(Scenario1.NB_ROUND) {
            allRoundItems.addAll(removeHugeFoods(Scenario1.shuffleList()))
        }
        assertFalse(Scenario1.isInvalid(allRoundItems))
    }


}