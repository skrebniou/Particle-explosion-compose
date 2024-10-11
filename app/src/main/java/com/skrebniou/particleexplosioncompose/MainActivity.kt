package com.skrebniou.particleexplosioncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.skrebniou.particleexplosioncompose.composables.AnimationManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val displayMetrics = getResources().getDisplayMetrics()
        setContent {
            AnimationManager((displayMetrics.widthPixels / displayMetrics.density).toInt())
        }
    }
}