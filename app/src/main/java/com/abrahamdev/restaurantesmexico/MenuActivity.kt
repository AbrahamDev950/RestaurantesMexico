package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar // <-- No olvides importar esto
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Toolbar personalizada
        val toolbar = findViewById<Toolbar>(R.id.toolbarMenu)
        setSupportActionBar(toolbar)

        // Botón de retroceso en la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtiene el ID del restaurante de los extras
        val restauranteId = intent.getIntExtra("RESTAURANTE_ID", -1)


        // Busca el restaurante por su ID
        val restaurante = RestauranteRepository
            .obtenerRestaurantes()
            .find { it.id == restauranteId }

        // Obtiene los datos del restaurante
        val txtNombre = findViewById<TextView>(R.id.txtNombreRestaurante)
        val txtId = findViewById<TextView>(R.id.txtIdRestaurante)

        // Rellena la vista con los datos del restaurante si lo encuentra
        if (restaurante != null) {
            txtNombre.text = restaurante.nombre
            txtId.text = "ID: ${restaurante.id}"
        } else {
            txtNombre.text = "Restaurante no encontrado"
            txtId.text = ""
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}