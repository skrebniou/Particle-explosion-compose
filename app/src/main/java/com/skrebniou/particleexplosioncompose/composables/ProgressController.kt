package com.skrebniou.particleexplosioncompose.composables

import android.animation.ValueAnimator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressController(progress: Float, updateProgress: (Float) -> Unit) {
    Column {
        Slider(
            value = progress,
            onValueChange = { updateProgress(it) }
        )
        Text(
            fontSize = 18.sp,
            text = "Progress: ${"%.2f".format(progress)}"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
            animator.duration = 1000 // 1 second

            animator.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                updateProgress(animatedValue)
            }

            animator.start()
        }) {
            Text(
                fontSize = 18.sp,
                text = "Play animation"
            )
        }
    }
}
