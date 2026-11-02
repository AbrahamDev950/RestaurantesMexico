package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.abrahamdev.restaurantesmexico.adapters.MenuPagerAdapter
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Recuperamos el ID del restaurante seleccionado
        val restauranteId = intent.getIntExtra(
            "RESTAURANTE_ID",
            -1
        )

        // Buscamos el restaurante
        val restaurante = RestauranteRepository
            .obtenerRestaurantes()
            .find { it.id == restauranteId }

        // Mostramos su nombre
        val txtNombre = findViewById<TextView>(
            R.id.txtNombreRestaurante
        )

        if (restaurante != null) {
            txtNombre.text = restaurante.nombre
        } else {
            txtNombre.text = "Restaurante no encontrado"
        }

        // Obtenemos las pestañas y el ViewPager2
        val tabLayout = findViewById<TabLayout>(
            R.id.tabLayout
        )

        val viewPager = findViewById<ViewPager2>(
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
}