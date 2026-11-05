package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductoDao {

    @Query("SELECT * FROM productos WHERE id = :productoId LIMIT 1")
    suspend fun obtenerProductoPorId(productoId: Int): ProductoEntity?
    @Query("""
        SELECT * FROM productos
        WHERE restauranteId = :restauranteId
        AND categoria = :categoria
        ORDER BY nombre ASC
    """)
    suspend fun obtenerProductos(
        restauranteId: Int,
        categoria: String
    ): List<ProductoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(productos: List<ProductoEntity>)
}