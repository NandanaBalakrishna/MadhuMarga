package com.example.madhumargaa.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.madhumargaa.data.Hive
import com.example.madhumargaa.ui.viewmodel.MadhuViewModel

@Composable
fun HiveDetailScreen(
    hiveId: String,
    viewModel: MadhuViewModel,
    onBack: () -> Unit,
    onGenerateLog: () -> Unit
) {
    // FIX: Specifying <Hive> explicitly solves the "Cannot infer type" error
    val hives by viewModel.hives.collectAsState(initial = emptyList<Hive>())
    val hive = hives.find { it.id == hiveId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // --- TOP NAVIGATION HEADER ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "←",
                    color = Color.Cyan,
                    modifier = Modifier.clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "[ EXECUTE_RETURN_TO_ROOT ]",
                    color = Color.Cyan,
                    fontSize = 10.sp
                )
            }

            OutlinedButton(
                onClick = onGenerateLog,
                border = BorderStroke(1.dp, Color.Yellow),
                shape = RectangleShape,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Yellow)
            ) {
                Text("GENERATE_NEW_LOG", fontSize = 10.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- IDENTITY SECTION ---
        Text(
            text = hive?.name?.uppercase() ?: "NEON COLONY",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold // FIXED: import added
        )
        Text(
            text = "LOC: ${hive?.location ?: "WEST MEADOW"} // ID: ${hive?.id ?: "H2"}",
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- TELEMETRY DASHBOARD ---
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text("UNIT TELEMETRY", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("HEALTH INTEGRITY", color = Color.Gray, fontSize = 10.sp)
                LinearProgressIndicator(
                    progress = hive?.healthIntegrity ?: 0.48f,
                    modifier = Modifier.fillMaxWidth().height(10.dp),
                    color = Color.Yellow,
                    trackColor = Color.DarkGray
                )
            }
        }
    }
}