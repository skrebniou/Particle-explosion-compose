package com.skrebniou.particleexplosioncompose.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skrebniou.particleexplosioncompose.entities.Config

@Composable
fun Configurator(
    config: Config,
    updateConfig: (Config) -> Unit,
    showErrorWithReason: (String?) -> Unit
) {
    var configParsable by remember { mutableStateOf(config.toConfigParsable()) }

    fun applyConfigBtnClick() {
        try {
            updateConfig(configParsable.toConfig())
        } catch (e: Exception) {
            showErrorWithReason("Cant apply this config. The exception is: $e. Double-check your entries.")
        }
    }

    Spacer(modifier = Modifier.height(40.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            fontSize = 24.sp,
            fontWeight = Bold,
            text = "Configuration"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Button(
            onClick = { applyConfigBtnClick() }
        ) {
            Text("Apply")
        }
    }
    OutlinedTextField(
        value = configParsable.particleAmount,
        onValueChange = { configParsable = configParsable.copy(particleAmount = it)},
        label = { Text("Particle Amount") }
    )
    OutlinedTextField(
        value = configParsable.color,
        onValueChange = { configParsable = configParsable.copy(color = it) },
        label = { Text("Color (HEX format)") }
    )
    OutlinedTextField(
        value = configParsable.velocityIndex,
        onValueChange = { configParsable = configParsable.copy(velocityIndex = it) },
        label = { Text("Velocity index (initial speed)") }
    )
    OutlinedTextField(
        value = configParsable.accelerationIndex,
        onValueChange = { configParsable = configParsable.copy(accelerationIndex = it) },
        label = { Text("Acceleration index (force of gravity)") }
    )
    OutlinedTextField(
        value = configParsable.initialDisplacementRange,
        onValueChange = { configParsable = configParsable.copy(initialDisplacementRange = it) },
        label = { Text("Initial displacement range (dp)") }
    )
    OutlinedTextField(
        value = configParsable.delayBeforeStartMax,
        onValueChange = { configParsable = configParsable.copy(delayBeforeStartMax = it) },
        label = { Text("Max delay before start (0-1)") }
    )
    OutlinedTextField(
        value = configParsable.delayBeforeEndMax,
        onValueChange = { configParsable = configParsable.copy(delayBeforeEndMax = it) },
        label = { Text("Max delay before end (0-1)") }
    )
    OutlinedTextField(
        value = configParsable.initialRadius,
        onValueChange = { configParsable = configParsable.copy(initialRadius = it) },
        label = { Text("Initial particle radius (dp)") }
    )
    OutlinedTextField(
        value = configParsable.minorityGroupMaxRadius,
        onValueChange = { configParsable = configParsable.copy(minorityGroupMaxRadius = it) },
        label = { Text("Minority group max particle radius (dp)") }
    )
    OutlinedTextField(
        value = configParsable.majorityGroupMaxRadius,
        onValueChange = { configParsable = configParsable.copy(majorityGroupMaxRadius = it) },
        label = { Text("Majority group max particle radius (dp)") }
    )
}