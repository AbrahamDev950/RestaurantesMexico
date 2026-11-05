package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.abrahamdev.restaurantesmexico.adapters.MenuPagerAdapter
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository
import com.google.android.material.appbar.MaterialToolbar
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.abrahamdev.restaurantesmexico.fragments.ProductosFragment

/**
 * Clase que representa la actividad después haber elegido un restaurante.
 */
class MenuActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    /**
     * Método que se ejecuta al crear la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        // Configuramos la Toolbar
        val toolbar = findViewById<MaterialToolbar>(
            R.id.toolbar
        )
        setSupportActionBar(toolbar)
        // Habilitamos el botón de retroceso
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Obtenemos el intent con el ID del restaurante seleccionado
        val restauranteId = intent.getIntExtra(
            "RESTAURANTE_ID",
            -1
        )

        // Buscamos el restaurante con ayuda de un repositorio
        val restaurante = RestauranteRepository
            .obtenerRestaurantes()
            .find { it.id == restauranteId }

        // Mostramos su nombre
        val txtNombre = findViewById<TextView>(
            R.id.txtNombreRestaurante
        )

        if (restaurante != null) {
            txtNombre.text = restaurante.nombre

            supportActionBar?.title = restaurante.nombre
        } else {
            txtNombre.text = "Restaurante no encontrado"
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

    /**
     * Método que infla el menú de opciones.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_busqueda, menu)

        // Configuramos la barra de búsqueda
        val itemBuscar = menu.findItem(R.id.action_buscar)

        val searchView = itemBuscar.actionView as SearchView

        searchView.queryHint = "Buscar producto..."

        // Listener para la búsqueda
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    // Dependiendo el fragmento visible, filtramos los productos
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
    /*
     * Método que se ejecuta al hacer clic en el botón de retroceso.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}