package com.abrahamdev.restaurantesmexico.data.local

import android.util.Log
import androidx.room.withTransaction
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository
import com.abrahamdev.restaurantesmexico.data.ProductoRepository

class DatabaseSeeder(
    private val database: AppDatabase
) {

    suspend fun inicializarDatos() {

        Log.d("DatabaseSeeder", "Inicializando datos...")
        val restaurantesExistentes =
            database.restauranteDao().obtenerTodos()
        Log.d("DatabaseSeeder", "Restaurantes existentes: $restaurantesExistentes")

        if (restaurantesExistentes.isNotEmpty()) {
            Log.d("DatabaseSeeder", "Los restaurantes ya existen en la base de datos.")
            return
        }

        Log.d("DatabaseSeeder", "Insertando restaurantes y productos...")
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
            Log.d("DatabaseSeeder", "Restaurantes y productos insertados correctamente.")
        }
    }
}