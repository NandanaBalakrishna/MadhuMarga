package com.example.madhumargaa.data

data class Hive(
    val id: String,
    val name: String,
    val location: String,
    val status: String, // e.g., "NORMAL ACTIVITY" or "WARNING"
    val healthIntegrity: Float, // 0.0f to 1.0f
    val honeyProduction: Double,
    val lastInspection: String,
    val sugarStores: String
)