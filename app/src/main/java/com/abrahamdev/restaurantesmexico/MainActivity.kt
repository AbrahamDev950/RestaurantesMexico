package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.abrahamdev.restaurantesmexico.adapters.RestauranteAdapter
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerRestaurantes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(
            R.id.toolbar
        )

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Restaurantes"
        recyclerRestaurantes =
            findViewById(R.id.recyclerRestaurantes)

        val restaurantes =
            RestauranteRepository.obtenerRestaurantes()

        recyclerRestaurantes.layoutManager =
            LinearLayoutManager(this)



        // Configurar el adaptador
        recyclerRestaurantes.adapter =
            RestauranteAdapter(restaurantes) { restaurante ->
                // Maneja el clic en un restaurante y lanza el MenuActivity
                val intent = Intent(this, MenuActivity::class.java)

                // Mandamos el ID del restaurante
                intent.putExtra("RESTAURANTE_ID", restaurante.id)

                startActivity(intent)
            }
    }
}
