package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.withTransaction
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository
import com.abrahamdev.restaurantesmexico.data.ProductoRepository

class DatabaseSeeder(
    private val database: AppDatabase
) {

    suspend fun inicializarDatos() {

        val restaurantesExistentes =
            database.restauranteDao().obtenerTodos()

        if (restaurantesExistentes.isNotEmpty()) {
            return
        }

        database.withTransaction {

            val restaurantes = RestauranteRepository
                .obtenerRestaurantes()
                .map { restaurante ->
                    RestauranteEntity(
                        id = restaurante.id,
                        nombre = restaurante.nombre,
                        direccion = restaurante.direccion,
                        descripcion = restaurante.descripcion
                    )
                }

            database.restauranteDao()
                .insertarTodos(restaurantes)

            val productos = RestauranteRepository
                .obtenerRestaurantes()
                .flatMap { restaurante ->
                    listOf("Comida", "Bebida", "Complemento")
                        .flatMap { categoria ->
                            ProductoRepository.obtenerProductos(
                                restaurante.id,
                                categoria
                            )
                        }
                }
                .map { producto ->
                    ProductoEntity(
                        id = producto.id,
                        restauranteId = producto.restauranteId,
                        nombre = producto.nombre,
                        precio = producto.precio,
                        descripcion = producto.descripcion,
                        categoria = producto.categoria
                    )
                }

            database.productoDao()
                .insertarTodos(productos)
        }
    }
}