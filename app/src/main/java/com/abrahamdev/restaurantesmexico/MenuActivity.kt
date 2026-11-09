package com.abrahamdev.restaurantesmexico

import com.abrahamdev.restaurantesmexico.data.local.DatabaseProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.abrahamdev.restaurantesmexico.adapters.MenuPagerAdapter
import com.google.android.material.appbar.MaterialToolbar
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.abrahamdev.restaurantesmexico.fragments.ProductosFragment
class MenuActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolbar = findViewById<MaterialToolbar>(
            R.id.toolbar
        )

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Recuperamos el ID del restaurante seleccionado
        val restauranteId = intent.getIntExtra("RESTAURANTE_ID", -1)

        // Mostramos el nombre del restaurante
        val txtNombre = findViewById<TextView>(
            R.id.txtNombreRestaurante
        )

        lifecycleScope.launch {

            val database = DatabaseProvider.getDatabase(this@MenuActivity)

            val restaurante = withContext(Dispatchers.IO) {
                Log.d("MenuActivity", "Buscando restaurante con ID: $restauranteId")
                database.restauranteDao().obtenerPorId(restauranteId)
                    .also { Log.d("MenuActivity", "Restaurante encontrado: $it") }
            }

            if (restaurante != null) {
                txtNombre.text = restaurante.nombre
                supportActionBar?.title = restaurante.nombre
            } else {
                txtNombre.text = "Restaurante no encontrado"
            }
        }

        // Obtenemos las pestañas y el ViewPager2
        val tabLayout = findViewById<TabLayout>(
            R.id.tabLayout
        )

        viewPager = findViewById(
            R.id.viewPager
        )

        // Creamos el adaptador
        val adapter = MenuPagerAdapter(
            this,
            restauranteId
        )

        viewPager.adapter = adapter

        // Conectamos las pestañas con las páginas
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->

            when (position) {
                0 -> tab.text = "Comida"
                1 -> tab.text = "Bebidas"
                2 -> tab.text = "Complementos"
            }

        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_busqueda, menu)

        val itemBuscar = menu.findItem(R.id.action_buscar)

        val searchView = itemBuscar.actionView as SearchView

        searchView.queryHint = "Buscar producto..."

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    val fragment = supportFragmentManager.fragments
                        .filterIsInstance<ProductosFragment>()
                        .firstOrNull { it.isResumed }

                    fragment?.filtrarProductos(newText.orEmpty())

                    return true
                }
            }
        )

        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}