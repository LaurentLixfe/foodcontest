package com.epsi.foodcontest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.epsi.foodcontest.models.WomanHealth
import com.epsi.foodcontest.ui.theme.FoodContestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            FoodContestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    FoodContestShow(
                        modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                        mainState = state
                    )

                }
            }
        }
    }
}

@Composable
fun FoodContestShow(modifier: Modifier = Modifier, mainState: MainState) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Food contest",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.Green.copy(green = 0.5f)
            )
        )
        Spacer(Modifier.height(16.dp))

        WomanImageFor(
            modifier = Modifier.size(128.dp),
            womanHealth = when (mainState.isGameEnded) {
                true -> mainState.womanHealthAtEnd
                false -> mainState.womanHealth
            }
        )
        if (mainState.isGameEnded) {
            Text(
                modifier = Modifier.fillMaxWidth(), text = "Game ended!",
                textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge
            )
        }else{
            var progress by remember { mutableFloatStateOf(0.1f) }
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = infiniteRepeatable(tween(
                    durationMillis = mainState.roundTime.toInt(),
                    easing = FastOutSlowInEasing,
                ),RepeatMode.Restart)
            )
            LaunchedEffect(mainState.roundTime) { progress = 1f }

            Spacer(Modifier.height(16.dp))
            LinearProgressIndicator(
                progress = { animatedProgress },
            )
        }
        Spacer(Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            mainState.plate.forEach {
                Box(
                    modifier = Modifier
                            .weight(1f)
                            .padding(3.dp)
                            .border(
                                width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp), contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(it.imageResId()),
                            contentDescription = null
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(text = "${it.name()}")
                        Spacer(Modifier.height(8.dp))
                        Text(text = "${it.weight()} g", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

@Composable
private fun WomanImageFor(modifier: Modifier = Modifier, womanHealth: WomanHealth) {
    val womanRes = when (womanHealth) {
        WomanHealth.EATING -> R.drawable.woman_eating
        WomanHealth.OBESE -> R.drawable.woman_obese
        WomanHealth.FOOD_POISONING_START -> R.drawable.woman_food_poisoning_start
        WomanHealth.FOOD_POISONING_END -> R.drawable.woman_food_poisoning_end
        WomanHealth.HAPPY -> R.drawable.woman_happy
    }
    Image(
        modifier = modifier, painter = painterResource(womanRes),
        contentDescription = null
    )
}