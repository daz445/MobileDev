package ru.dmitryzyrynov.lab15

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val name: String
)