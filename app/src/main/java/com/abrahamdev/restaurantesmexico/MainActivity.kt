package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.abrahamdev.restaurantesmexico.adapters.RestauranteAdapter
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository
import android.view.Menu
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerRestaurantes: RecyclerView
    private lateinit var restauranteAdapter: RestauranteAdapter

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
        restauranteAdapter = RestauranteAdapter(restaurantes) { restaurante ->

            val intent = Intent(this, MenuActivity::class.java)

            intent.putExtra("RESTAURANTE_ID", restaurante.id)

            startActivity(intent)
        }

        recyclerRestaurantes.adapter = restauranteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_busqueda, menu)

        val itemBuscar = menu.findItem(R.id.action_buscar)

        val searchView = itemBuscar.actionView as SearchView

        searchView.queryHint = "Buscar restaurante..."

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    restauranteAdapter.filtrar(newText.orEmpty())
                    return true
                }
            }
        )

        return true
    }
}
