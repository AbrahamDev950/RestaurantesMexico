package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.adapters.RestauranteAdapter
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerRestaurantes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerRestaurantes =
            findViewById(R.id.recyclerRestaurantes)

        val restaurantes =
            RestauranteRepository.obtenerRestaurantes()

        recyclerRestaurantes.layoutManager =
            LinearLayoutManager(this)

        recyclerRestaurantes.adapter =
            RestauranteAdapter(restaurantes)
    }
}
