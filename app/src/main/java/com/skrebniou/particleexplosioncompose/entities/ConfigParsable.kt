package com.skrebniou.particleexplosioncompose.entities

import com.skrebniou.particleexplosioncompose.hexToColor

data class ConfigParsable(
    var xPos: String,
    var yPos: String,
    var size: String,
    var particleAmount: String,
    var color: String,
    var velocityIndex: String,
    var accelerationIndex: String,
    var initialDisplacementRange: String,
    var delayBeforeStartMax: String,
    var delayBeforeEndMax: String,
    var initialRadius: String,
    var minorityGroupMaxRadius: String,
    var majorityGroupMaxRadius: String
) {
    fun toConfig(): Config {
        return Config(
            xPos = this.xPos.toInt(),
            yPos = this.yPos.toInt(),
            size = this.size.toInt(),
            particleAmount = this.particleAmount.toInt(),
            color = hexToColor(this.color),
            velocityIndex = this.velocityIndex.toFloat(),
            accelerationIndex = this.accelerationIndex.toFloat(),
            initialDisplacementRange = this.initialDisplacementRange.toFloat(),
            delayBeforeStartMax = this.delayBeforeStartMax.toFloat(),
            delayBeforeEndMax = this.delayBeforeEndMax.toFloat(),
            initialRadius = this.initialRadius.toFloat(),
            minorityGroupMaxRadius = this.minorityGroupMaxRadius.toFloat(),
            majorityGroupMaxRadius = this.majorityGroupMaxRadius.toFloat()
        )
    }
}
