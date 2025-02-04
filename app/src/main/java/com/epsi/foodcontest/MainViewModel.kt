package com.epsi.foodcontest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epsi.foodcontest.business.Scenario2
import com.epsi.foodcontest.models.WomanHealth
import com.epsi.foodcontest.utils.removeHugeFoods
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val currentScenario = Scenario2
    var state = MutableStateFlow(MainState(roundTime = currentScenario.TIME_BETWEEN_PLATE))
        private set

    init {

        flow {
            repeat(currentScenario.NB_ROUND) {
                emit(currentScenario.shuffleList())
                delay(currentScenario.TIME_BETWEEN_PLATE)
            }
        }
                .onEach { newPlate ->
                    val filteredPlate = removeHugeFoods(newPlate).toList()
                    var health = WomanHealth.EATING
                    if (currentScenario.isInvalid(filteredPlate)) {
                        health = WomanHealth.FOOD_POISONING_START
                    }
                    state.update {
                        it.copy(
                            plate = filteredPlate,
                            womanHealth = health,
                            womanHealthAtEnd = when (health) {
                                WomanHealth.FOOD_POISONING_START -> WomanHealth.FOOD_POISONING_END
                                else -> it.womanHealthAtEnd
                            }
                        )
                    }
                }
                .onCompletion {
                    state.update { it.copy(isGameEnded = true, plate = emptyList()) }
                }
                .launchIn(viewModelScope)


    }


}

