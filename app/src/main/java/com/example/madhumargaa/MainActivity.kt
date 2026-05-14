package com.example.madhumargaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// --- TECH THEME COLORS ---
val CyberBlack = Color(0xFF050505)
val CyberYellow = Color(0xFFFFD700)
val CyberCyan = Color(0xFF00E5FF)
val CyberDarkGray = Color(0xFF121212)

data class HiveLog(val date: String, val activity: String, val note: String, val queen: Boolean, val pests: String)
data class FloraMonth(val month: String, val plants: String, val status: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val logs = remember { mutableStateListOf<HiveLog>() }

            Surface(color = CyberBlack, modifier = Modifier.fillMaxSize()) {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") { LoginScreen(navController) }
                    composable("signup") { SignupScreen(navController) }
                    composable("registry") { RegistryScreen(navController) }
                    composable("telemetry") { TelemetryScreen(navController, logs) }
                    composable("new_log") {
                        // Fixed lambda to resolve NavBackStackEntry mismatch
                        NewLogScreen(navController) { newLog: HiveLog ->
                            logs.add(0, newLog)
                        }
                    }
                    composable("flora") { FloraGridScreen(navController) }
                }
            }
        }
    }
}

// --- 1. LOGIN SCREEN ---
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(Modifier.fillMaxSize().padding(24.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(60.dp))
        Text("MADHU MARGA", color = CyberYellow, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
        Text("SECURE APIARY ACCESS", color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(50.dp))

        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email Address") }, modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedLabelColor = CyberYellow, unfocusedLabelColor = Color.Gray,
                focusedBorderColor = CyberCyan, unfocusedBorderColor = Color.DarkGray
            )
        )
        Spacer(Modifier.height(15.dp))

        OutlinedTextField(
            value = pass, onValueChange = { pass = it },
            label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                focusedLabelColor = CyberYellow, unfocusedLabelColor = Color.Gray,
                focusedBorderColor = CyberCyan, unfocusedBorderColor = Color.DarkGray
            )
        )
        Spacer(Modifier.height(40.dp))
        Button(onClick = { focusManager.clearFocus(); navController.navigate("registry") }, modifier = Modifier.fillMaxWidth().height(50.dp), colors = ButtonDefaults.buttonColors(CyberYellow)) {
            Text("AUTHORIZE ACCESS", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("signup") }) {
            Text("NEW BEEKEEPER? SIGN UP HERE", color = CyberCyan, fontSize = 13.sp)
        }
    }
}

// --- 2. SIGNUP SCREEN ---
@Composable
fun SignupScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("CREATE ACCOUNT", color = CyberYellow, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(30.dp))
        OutlinedTextField(
            value = name, onValueChange = { name = it },
            label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = CyberCyan)
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = email, onValueChange = { email = it },
            label = { Text("Email") }, modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = CyberCyan)
        )
        Spacer(Modifier.height(30.dp))
        Button(onClick = { navController.navigate("registry") }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(CyberCyan)) {
            Text("REGISTER & ENTER", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        TextButton(onClick = { navController.popBackStack() }) {
            Text("BACK TO LOGIN", color = Color.Gray)
        }
    }
}

// --- 3. REGISTRY SCREEN ---
@Composable
fun RegistryScreen(navController: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Top) {
            Column {
                Text("CENTRAL REGISTRY", color = Color.Gray, fontSize = 12.sp)
                Text("ACTIVE APIARY UNITS", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = {
                navController.navigate("login") { popUpTo("registry") { inclusive = true } }
            }) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, "Logout", tint = Color.Red)
            }
        }

        Spacer(Modifier.height(20.dp))

        Box(Modifier.fillMaxWidth().border(1.dp, CyberYellow).background(CyberDarkGray).clickable { navController.navigate("telemetry") }.padding(16.dp)) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("UNIT_H1 // ", color = Color.Gray, fontSize = 10.sp)
                    Text("HEALTHY", color = Color.Green, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Text("GILDED SWARM", color = CyberYellow, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("📍 NORTH ORCHARD SECTOR", color = CyberCyan, fontSize = 11.sp)
                Spacer(Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("STATUS: NOMINAL", color = Color.Gray, fontSize = 10.sp)
                    Text("OPEN TELEMETRY >", color = CyberYellow, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("flora") }, modifier = Modifier.fillMaxWidth(), border = BorderStroke(1.dp, CyberCyan), colors = ButtonDefaults.outlinedButtonColors()) {
            Text("ENVIRONMENTAL SCAN (FLORA)", color = CyberCyan)
        }
    }
}

// --- 4. TELEMETRY SCREEN ---
@Composable
fun TelemetryScreen(navController: NavController, logs: List<HiveLog>) {
    val lastLog = logs.firstOrNull()
    val floraStatus = lastLog?.activity ?: "MED"
    val healthValue = if (lastLog?.queen == true) "98%" else if (lastLog == null) "100%" else "75%"
    val healthColor = if (healthValue == "75%") Color.Yellow else Color.Green

    val aiGuidance = when (lastLog?.activity) {
        "HIGH" -> "AI: Peak nectar flow detected. Ensure adequate space."
        "LOW" -> "AI: Activity drop. Correlate with Flora calendar."
        else -> "AI: System stable. Continue monitoring."
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
            }
            Text("UNIT_H1 TELEMETRY", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { navController.navigate("new_log") }) {
                Icon(Icons.Default.Add, "New Log", tint = CyberYellow)
            }
        }

        Spacer(Modifier.height(10.dp))
        Box(Modifier.fillMaxWidth().background(CyberDarkGray).border(1.dp, CyberCyan).padding(12.dp)) {
            Column {
                Text("ENVIRONMENTAL SUMMARY", color = CyberCyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("TEMP: 24°C", color = Color.White, fontSize = 12.sp)
                        Text("HUMID: 45%", color = Color.White, fontSize = 12.sp)
                    }
                    Column {
                        Text("FLORA: $floraStatus", color = Color.White, fontSize = 12.sp)
                        Text("HEALTH: $healthValue", color = healthColor, fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(15.dp))
        Box(Modifier.fillMaxWidth().border(1.dp, Color.DarkGray).padding(8.dp)) {
            Text(aiGuidance, color = CyberCyan, fontSize = 12.sp)
        }

        Spacer(Modifier.height(20.dp))
        Text("HISTORICAL LOGS", color = CyberYellow, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        HorizontalDivider(Modifier.padding(vertical = 8.dp), color = Color.DarkGray)

        LazyColumn(Modifier.fillMaxSize()) {
            items(logs) { log ->
                Column(Modifier.padding(vertical = 10.dp)) {
                    Text("${log.date} | QUEEN: ${if(log.queen) "SEEN" else "NOT SEEN"}", color = Color.Gray, fontSize = 11.sp)
                    Text("${log.activity} ACTIVITY", color = CyberYellow, fontWeight = FontWeight.Bold)
                    Text(log.note, color = Color.White, fontSize = 14.sp)
                }
                HorizontalDivider(color = Color(0xFF1A1A1A))
            }
        }
    }
}

// --- 5. NEW LOG SCREEN ---
@Composable
fun NewLogScreen(navController: NavController, onTransmit: (HiveLog) -> Unit) {
    var notes by remember { mutableStateOf("") }
    var activityLevel by remember { mutableStateOf("NORMAL") }
    var queenSeen by remember { mutableStateOf(false) }
    var pestLevel by remember { mutableStateOf("NONE") }
    val focusManager = LocalFocusManager.current

    Column(Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
            }
            Text("NEW SYSTEM LOG", color = CyberYellow, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(10.dp))
        Column(Modifier.border(1.dp, CyberYellow).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = queenSeen, onCheckedChange = { queenSeen = it }, colors = CheckboxDefaults.colors(CyberCyan))
                Text("QUEEN OBSERVED", color = Color.White)
            }
            Spacer(Modifier.height(20.dp))
            Text("ACTIVITY LEVEL", color = Color.Gray, fontSize = 12.sp)
            Row(Modifier.fillMaxWidth()) {
                listOf("LOW", "NORMAL", "HIGH").forEach { level ->
                    Button(
                        onClick = { activityLevel = level },
                        modifier = Modifier.weight(1f).padding(4.dp),
                        colors = ButtonDefaults.buttonColors(if (activityLevel == level) CyberYellow else CyberDarkGray)
                    ) { Text(level, color = if(activityLevel == level) Color.Black else Color.White, fontSize = 10.sp) }
                }
            }
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = pestLevel, onValueChange = { pestLevel = it },
                label = {Text("PEST DETECTION")}, modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = CyberCyan)
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = notes, onValueChange = { notes = it },
                label = { Text("OBSERVATION NOTES") }, modifier = Modifier.fillMaxWidth().height(120.dp),
                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = CyberCyan)
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = {
                    focusManager.clearFocus()
                    onTransmit(HiveLog("13/05/2026", activityLevel, notes, queenSeen, pestLevel))
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.buttonColors(CyberYellow)
            ) { Text("TRANSMIT LOG", color = Color.Black, fontWeight = FontWeight.Bold) }
        }
        Spacer(Modifier.height(40.dp))
    }
}

// --- 6. FLORA GRID SCREEN ---
@Composable
fun FloraGridScreen(navController: NavController) {
    val months = listOf(
        FloraMonth("JAN", "Mustard", "MED"), FloraMonth("FEB", "Sunflower", "HIGH"),
        FloraMonth("MAR", "Mango", "HIGH"), FloraMonth("APR", "Citrus", "HIGH"),
        FloraMonth("MAY", "Lavender", "PEAK"), FloraMonth("JUN", "Wild Herbs", "LOW"),
        FloraMonth("JUL", "Clover", "MED"), FloraMonth("AUG", "Maize", "MED"),
        FloraMonth("SEP", "Buckwheat", "HIGH"), FloraMonth("OCT", "Ivy", "HIGH"),
        FloraMonth("NOV", "Goldenrod", "MED"), FloraMonth("DEC", "Winter Gorse", "LOW")
    )

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
            }
            Text("FLORA DISTRIBUTION", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.weight(1f)) {
            items(months) { item ->
                Card(Modifier.padding(4.dp), colors = CardDefaults.cardColors(CyberDarkGray)) {
                    Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(item.month, color = CyberYellow, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Text(item.plants, color = Color.White, fontSize = 10.sp, maxLines = 1)
                        Text(item.status, color = CyberCyan, fontSize = 9.sp)
                    }
                }
            }
        }
    }
}