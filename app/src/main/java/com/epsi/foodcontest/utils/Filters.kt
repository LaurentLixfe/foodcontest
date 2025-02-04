package com.epsi.foodcontest.utils


fun removeHugeFoods(foodsHeight: List<Int>): MutableList<Int> {
    val filteredFoods = mutableListOf<Int>()

    //TODO: Ecrire la fonction pour garder uniquement les aliments de moins de 600g
    // TODO: Effacer cette ligne pour commencer⬇
    filteredFoods.addAll(foodsHeight)

    return filteredFoods
}