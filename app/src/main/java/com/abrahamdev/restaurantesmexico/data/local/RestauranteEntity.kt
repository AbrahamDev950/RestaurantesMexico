package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurantes")
data class RestauranteEntity(

    @PrimaryKey
    val id: Int,

    val nombre: String,

    val direccion: String,

    val descripcion: String
)