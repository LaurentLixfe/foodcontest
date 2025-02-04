package com.epsi.foodcontest.models

interface Comestible {
    fun weight(): Int
    fun name(): String
    fun imageResId() : Int
}

