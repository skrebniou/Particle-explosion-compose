package com.skrebniou.particleexplosioncompose.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skrebniou.particleexplosioncompose.entities.Config
import com.skrebniou.particleexplosioncompose.entities.Particle
import com.skrebniou.particleexplosioncompose.hexToColor
import com.skrebniou.particleexplosioncompose.randomInRange
import com.skrebniou.particleexplosioncompose.toPx

@Composable
fun ExplosionRenderer(progress: Float, config: Config) {
    val sizeDp = config.size.dp
    val sizePx = sizeDp.toPx()
    val sizePxHalf = sizePx / 2
    val particles = remember(config) {
        List(config.particleAmount) {
            Particle(
                config.color,
                config.xPos,
                config.yPos,
                sizePx * randomInRange(-0.9f, 0.9f),
                sizePx * randomInRange(0.2f, 0.38f),
                config.velocityIndex,
                config.accelerationIndex,
                config.initialDisplacementRange,
                config.delayBeforeStartMax,
                config.delayBeforeEndMax,
                config.initialRadius,
                config.minorityGroupMaxRadius,
                config.majorityGroupMaxRadius
            )
        }
    }
    particles.forEach { it.updateProgress(progress) }

    Canvas(
        modifier = Modifier
            .border(width = 1.dp, color = Color(0x26000000))
            .size(sizeDp)
    ) {
        drawLine(
            color = Color.Gray,
            start = Offset(sizePxHalf, 0f),
            end = Offset(sizePxHalf, sizePx),
            strokeWidth = 2.dp.toPx()
        )
        drawLine(
            color = Color.Gray,
            start = Offset(0f, sizePxHalf),
            end = Offset(sizePx, sizePxHalf),
            strokeWidth = 2.dp.toPx()
        )
        particles.forEach { particle ->
            drawCircle(
                alpha = particle.alpha,
                color = particle.color,
                radius = particle.currentRadius,
                center = Offset(particle.currentXPosition, particle.currentYPosition),
            )
        }
    }
}