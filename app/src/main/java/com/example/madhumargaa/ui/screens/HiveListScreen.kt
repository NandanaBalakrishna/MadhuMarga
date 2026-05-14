package com.example.madhumargaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhumargaa.data.Hive
import com.example.madhumargaa.ui.components.HiveCard
import com.example.madhumargaa.ui.viewmodel.MadhuViewModel

@Composable
fun HiveListScreen(
    viewModel: MadhuViewModel,
    onLogout: () -> Unit,
    onHiveClick: (String) -> Unit
) {
    // FIXED: Added healthIntegrity, lastInspection, and sugarStores to match your new data class
    val hive = Hive(
        id = "H1",
        name = "GILDED SWARM",
        location = "NORTH ORCHARD",
        status = "HEALTHY",
        healthIntegrity = 0.9f,      // Added
        honeyProduction = 12.5,
        lastInspection = "25/04/2026", // Added
        sugarStores = "82%"          // Added
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { onLogout() }) {
                Text("Logout")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // This card will now correctly navigate when clicked
        HiveCard(
            hive = hive,
            onClick = {
                onHiveClick(hive.id)
            }
        )
    }
}