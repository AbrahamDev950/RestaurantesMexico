package com.abrahamdev.restaurantesmexico.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RestauranteEntity::class,
        ProductoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restauranteDao(): RestauranteDao

    abstract fun productoDao(): ProductoDao
}