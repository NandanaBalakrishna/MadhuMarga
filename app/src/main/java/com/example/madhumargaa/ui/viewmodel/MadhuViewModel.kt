package com.example.madhumargaa.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.madhumargaa.data.Hive

class MadhuViewModel : ViewModel() {
    private val _hives = MutableStateFlow<List<Hive>>(
        listOf(
            Hive(
                id = "H1",
                name = "GILDED SWARM",
                location = "NORTH ORCHARD",
                status = "HEALTHY",
                healthIntegrity = 0.9f,
                honeyProduction = 12.5,
                lastInspection = "25/4/2026",
                sugarStores = "82%"
            ),
            // ADDED: NEON COLONY
            Hive(
                id = "H2",
                name = "NEON COLONY",
                location = "WEST MEADOW",
                status = "WARNING",
                healthIntegrity = 0.48f,
                honeyProduction = 5.2,
                lastInspection = "28/4/2026",
                sugarStores = "48%"
            )
        )
    )
    val hives: StateFlow<List<Hive>> = _hives
}