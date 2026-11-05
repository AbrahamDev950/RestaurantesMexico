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

    // Usamos RecyclerView para mostrar los restaurantes ya que pueden ser muchos
    private lateinit var recyclerRestaurantes: RecyclerView
    // Usamos un adaptador para mostrar los restaurantes
    private lateinit var restauranteAdapter: RestauranteAdapter

    /*
     * Método que se ejecuta al crear la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuramos la Toolbar
        val toolbar = findViewById<MaterialToolbar>(
            R.id.toolbar
        )
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Restaurantes"

        // Obtenemos el RecyclerView
        recyclerRestaurantes =
            findViewById(R.id.recyclerRestaurantes)

        // Obtenemos los restaurantes
        val restaurantes =
            RestauranteRepository.obtenerRestaurantes()

        // Configuramos el RecyclerView con un LinearLayoutManager
        recyclerRestaurantes.layoutManager =
            LinearLayoutManager(this)


        // Configurar el adaptador con el restaurante seleccionado
        restauranteAdapter = RestauranteAdapter(restaurantes) { restaurante ->
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("RESTAURANTE_ID", restaurante.id)
            startActivity(intent)
        }

        recyclerRestaurantes.adapter = restauranteAdapter
    }

    /*
     * Método que infla el menú de opciones.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_busqueda, menu)

        // Configuramos la barra de búsqueda
        val itemBuscar = menu.findItem(R.id.action_buscar)
        val searchView = itemBuscar.actionView as SearchView
        searchView.queryHint = "Buscar restaurante..."

        // Listener para la búsqueda
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
