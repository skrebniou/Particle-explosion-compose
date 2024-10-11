package com.skrebniou.particleexplosioncompose

import android.content.res.Resources
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import java.util.Random

fun Float.mapInRange(inMin: Float, inMax: Float, outMin: Float, outMax: Float): Float {
    return outMin + (((this - inMin) / (inMax - inMin)) * (outMax - outMin))
}

fun Int.dpToPx() = toFloat().dpToPx()
fun Dp.toPx() = value.dpToPx()

fun Float.dpToPx() = this * Resources.getSystem().displayMetrics.density

private val random = Random()

fun Float.randomTillZero() = this * random.nextFloat()

fun randomInRange(min: Float, max: Float) = min + (max - min).randomTillZero()

fun randomBoolean(trueProbabilityPercentage: Int) =
    random.nextFloat() < trueProbabilityPercentage / 100f

fun hexToColor(hex: String): Color {
    val cleanHex = hex.removePrefix("#")
    if (!cleanHex.matches(Regex("[0-9A-Fa-f]{6}([0-9A-Fa-f]{2})?"))) {
        throw IllegalArgumentException("Invalid color format. Use a valid HEX code (e.g., #RRGGBB or #AARRGGBB).")
    }
    return try {
        val argbColor = if (cleanHex.length == 6) {
            "FF$cleanHex"
        } else {
            cleanHex
        }.toLong(16)
        Color(argbColor.toInt())
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid HEX color value.")
    }
}

fun colorToHex(color: Color): String {
    val alpha = (color.alpha * 255).toInt()
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()

    return String.format("#%02X%02X%02X%02X", alpha, red, green, blue)
}