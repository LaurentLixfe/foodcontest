package com.epsi.foodcontest.utils

import com.epsi.foodcontest.models.Comestible


fun removeHugeFoods(foods: List<Comestible>): MutableList<Comestible> {
    val filteredFoods = mutableListOf<Comestible>()

    //TODO: Ecrire la fonction pour garder uniquement les aliments de moins de 600g
    filteredFoods.addAll(foods)

    return filteredFoods
}