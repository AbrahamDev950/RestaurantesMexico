package com.abrahamdev.restaurantesmexico

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import com.abrahamdev.restaurantesmexico.data.Restaurante
import com.abrahamdev.restaurantesmexico.data.local.DatabaseProvider
import com.abrahamdev.restaurantesmexico.data.local.DatabaseSeeder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.abrahamdev.restaurantesmexico.adapters.RestauranteAdapter
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

        recyclerRestaurantes.layoutManager =
            LinearLayoutManager(this)

        lifecycleScope.launch {

            val database = DatabaseProvider.getDatabase(this@MainActivity)

            val restaurantes = withContext(Dispatchers.IO) {

                // 1. Comprobar e insertar los datos iniciales.
                DatabaseSeeder(database).inicializarDatos()

                // 2. Consultar los restaurantes almacenados en Room.
                database.restauranteDao().obtenerTodos()
            }

            // 3. Convertir las entidades de Room a nuestros modelos.
            val restaurantesModelo = restaurantes.map { entidad ->
                Restaurante(
                    id = entidad.id,
                    nombre = entidad.nombre,
                    direccion = entidad.direccion,
                    descripcion = entidad.descripcion
                )
            }

            // 4. Mostrar los restaurantes.
            restauranteAdapter = RestauranteAdapter(restaurantesModelo) { restaurante ->

                val intent = Intent(this@MainActivity, MenuActivity::class.java)

                intent.putExtra("RESTAURANTE_ID", restaurante.id)

                startActivity(intent)
            }

            recyclerRestaurantes.adapter = restauranteAdapter
        }    }

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
