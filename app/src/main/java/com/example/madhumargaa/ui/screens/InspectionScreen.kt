package com.example.madhumargaa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InspectionLogScreen(

    onSaveReport: () -> Unit

) {

    var report by remember {

        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        Text(

            text = "Inspection Report",

            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = report,

            onValueChange = {

                report = it
            },

            label = {

                Text("Enter Report")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = {

                onSaveReport()
            }

        ) {

            Text("Save Report")
        }
    }
}
