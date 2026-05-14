package com.example.madhumargaa.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.madhumargaa.data.Hive

@Composable
fun HiveCard(
    hive: Hive,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick()
            }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(text = "UNIT: ${hive.name}")

            Text(text = "Location: ${hive.location}")

            Text(text = "Honey Production: 0.0 kg")
        }
    }
}
