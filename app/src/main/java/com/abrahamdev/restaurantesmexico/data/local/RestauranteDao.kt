package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestauranteDao {

    @Query("SELECT * FROM restaurantes WHERE id = :restauranteId LIMIT 1")
    suspend fun obtenerPorId(restauranteId: Int): RestauranteEntity?
    @Query("SELECT * FROM restaurantes ORDER BY nombre ASC")
    suspend fun obtenerTodos(): List<RestauranteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(restaurantes: List<RestauranteEntity>)
}