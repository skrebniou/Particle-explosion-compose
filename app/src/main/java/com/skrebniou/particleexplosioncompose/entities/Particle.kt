package com.skrebniou.particleexplosioncompose.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skrebniou.particleexplosioncompose.mapInRange
import com.skrebniou.particleexplosioncompose.randomBoolean
import com.skrebniou.particleexplosioncompose.randomInRange
import com.skrebniou.particleexplosioncompose.toPx
import kotlin.math.pow

class Particle(
    val color: Color,
    val startXPosition: Int,
    val startYPosition: Int,
    val maxHorizontalDisplacement: Float,
    val maxVerticalDisplacement: Float,
    val velocityIndex: Float, // def = 1f
    val accelerationIndex: Float, // def = 2f
    val initialDisplacementRange: Float, // def = 10f
    val delayBeforeStartMax: Float, // def = 0.14f
    val delayBeforeEndMax: Float, // def = 0.4f
    val initialRadius: Float, // def = 2f
    val minorityGroupMaxRadius: Float, // def = 7f
    val majorityGroupMaxRadius: Float // def = 1.5f
) {
    val velocity = velocityIndex * 4 * maxVerticalDisplacement
    val acceleration = -accelerationIndex * velocity
    var currentXPosition = 0f
    var currentYPosition = 0f

    var delayBeforeStart = randomInRange(0f, delayBeforeStartMax)
    var delayBeforeEnd = randomInRange(0f, delayBeforeEndMax)

    val initialXDisplacement = initialDisplacementRange.dp.toPx() * randomInRange(-1f, 1f)
    val initialYDisplacement = initialDisplacementRange.dp.toPx() * randomInRange(-1f, 1f)

    var alpha = 0f
    var currentRadius = 0f
    val initialRadiusPx = initialRadius.dp.toPx()
    val endRadius = if (randomBoolean(trueProbabilityPercentage = 20)) {
        randomInRange(initialRadiusPx, minorityGroupMaxRadius.dp.toPx())
    } else {
        randomInRange(majorityGroupMaxRadius.dp.toPx(), initialRadiusPx)
    }

    fun updateProgress(explosionProgress: Float) {
        val trajectoryProgress =
            if (explosionProgress < delayBeforeStart || (explosionProgress > (1 - delayBeforeEnd))) {
                alpha = 0f; return
            } else (explosionProgress - delayBeforeStart).mapInRange(0f,1f - delayBeforeEnd - delayBeforeStart,0f, 1f)
        alpha = if (trajectoryProgress < 0.7f) 1f else (trajectoryProgress - 0.7f).mapInRange(
            0f,
            0.3f,
            1f,
            0f
        )
        currentRadius = initialRadiusPx + (endRadius - initialRadiusPx) * trajectoryProgress
        val currentTime = trajectoryProgress.mapInRange(0f, 1f, 0f, 1.4f)
        val verticalDisplacement =
            (currentTime * velocity + 0.5 * acceleration * currentTime.toDouble()
                .pow(2.0)).toFloat()
        currentYPosition = startYPosition + initialYDisplacement - verticalDisplacement
        currentXPosition =
            startXPosition + initialXDisplacement + maxHorizontalDisplacement * trajectoryProgress
    }
}
