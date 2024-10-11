package com.skrebniou.particleexplosioncompose.entities

import androidx.compose.ui.graphics.Color
import com.skrebniou.particleexplosioncompose.colorToHex

data class Config(
    var size: Int,
    var particleAmount: Int,
    var color: Color,
    var velocityIndex: Float,
    var accelerationIndex: Float,
    var initialDisplacementRange: Float,
    var delayBeforeStartMax: Float,
    var delayBeforeEndMax: Float,
    var initialRadius: Float,
    var minorityGroupMaxRadius: Float,
    var majorityGroupMaxRadius: Float
) {
    fun toConfigParsable(): ConfigParsable {
        return ConfigParsable(
            size = this.size.toString(),
            particleAmount = this.particleAmount.toString(),
            color = colorToHex(this.color),
            velocityIndex = this.velocityIndex.toString(),
            accelerationIndex = this.accelerationIndex.toString(),
            initialDisplacementRange = this.initialDisplacementRange.toString(),
            delayBeforeStartMax = this.delayBeforeStartMax.toString(),
            delayBeforeEndMax = this.delayBeforeEndMax.toString(),
            initialRadius = this.initialRadius.toString(),
            minorityGroupMaxRadius = this.minorityGroupMaxRadius.toString(),
            majorityGroupMaxRadius = this.majorityGroupMaxRadius.toString()
        )
    }
}
