package com.skrebniou.particleexplosioncompose.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button

@Composable
fun ErrorDialogDisplayer(reason: String?, updateReason: (String?) -> Unit) {
    AlertDialog(
        onDismissRequest = { updateReason(null) },
        title = { Text("Oops!") },
        text = { Text(reason ?: "Nevermind.") },
        confirmButton = {
            Button(onClick = { updateReason(null) }) {
                Text("OK")
            }
        }
    )
}