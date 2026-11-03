package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "productos",
    foreignKeys = [
        ForeignKey(
            entity = RestauranteEntity::class,
            parentColumns = ["id"],
            childColumns = ["restauranteId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("restauranteId")]
)
data class ProductoEntity(

    @PrimaryKey
    val id: Int,

    val restauranteId: Int,

    val nombre: String,

    val precio: Double,

    val descripcion: String,

    val categoria: String
)