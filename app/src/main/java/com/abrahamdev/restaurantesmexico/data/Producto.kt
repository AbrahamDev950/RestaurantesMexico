package com.abrahamdev.restaurantesmexico.data

data class Producto(
    val id: Int,
    val restauranteId: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val categoria: String
)