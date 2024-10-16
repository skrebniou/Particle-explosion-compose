package com.skrebniou.particleexplosioncompose.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skrebniou.particleexplosioncompose.entities.Config
import com.skrebniou.particleexplosioncompose.toPx

@Composable
fun AnimationManager(size: Int) {
    var errorDialogReason by remember { mutableStateOf<String?>(null) }
    var animationProgress by remember { mutableFloatStateOf(0.0f) }

    var config by remember {
        mutableStateOf(
            Config(
                xPos = (size.dp.toPx() / 2).toInt(),
                yPos = (size.dp.toPx() / 2).toInt(),
                size = size,
                particleAmount = 150,
                color = Color.Red,
                velocityIndex = 1f,
                accelerationIndex = 2f,
                initialDisplacementRange = 10f,
                delayBeforeStartMax = 0.14f,
                delayBeforeEndMax = 0.4f,
                initialRadius = 2f,
                minorityGroupMaxRadius = 7f,
                majorityGroupMaxRadius = 1.5f
            )
        )
    }

    fun updateErrorDialog(newReason: String?) {
        errorDialogReason = newReason
    }

    fun updateConfig(newConfig: Config) {
        config = newConfig
    }

    fun updateProgress(newProgress: Float) {
        animationProgress = newProgress
    }

    if(errorDialogReason != null) {
        ErrorDialogDisplayer(errorDialogReason, ::updateErrorDialog)
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .navigationBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ExplosionRenderer(animationProgress, config)
        ProgressController(animationProgress, ::updateProgress)
        Configurator(config, ::updateConfig, ::updateErrorDialog)
    }
}
