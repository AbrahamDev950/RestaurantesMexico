package com.abrahamdev.restaurantesmexico

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abrahamdev.restaurantesmexico.data.ProductoRepository
import com.abrahamdev.restaurantesmexico.data.RestauranteRepository
import com.google.android.material.appbar.MaterialToolbar
/*
 * Clase que representa la actividad cuando se selecciona un producto
 * y se muestra su detalle.
 */
class DetalleProductoActivity : AppCompatActivity() {

    /*
     * Método que se ejecuta al crear la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        // Configuramos la Toolbar
        val toolbar = findViewById<MaterialToolbar>(
            R.id.toolbar
        )
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detalle del producto"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Recuperamos el ID enviado desde la pantalla anterior
        val productoId = intent.getIntExtra(
            "PRODUCTO_ID",
            -1
        )

        // Buscamos el producto
        val producto = ProductoRepository
            .obtenerProductoPorId(productoId)

        if (producto != null) {

            // Buscamos el restaurante al que pertenece
            val restaurante = RestauranteRepository
                .obtenerRestaurantes()
                .find { it.id == producto.restauranteId }

            // Referencias a los elementos visuales
            val txtNombre = findViewById<TextView>(
                R.id.txtDetalleNombre
            )
            val txtPrecio = findViewById<TextView>(
                R.id.txtDetallePrecio
            )
            val txtDescripcion = findViewById<TextView>(
                R.id.txtDetalleDescripcion
            )
            val txtCategoria = findViewById<TextView>(
                R.id.txtDetalleCategoria
            )
            val txtRestaurante = findViewById<TextView>(
                R.id.txtDetalleRestaurante
            )


            // Mostramos la información del producto en la pantalla
            txtNombre.text = producto.nombre
            txtPrecio.text = "$${"%.2f".format(producto.precio)}"
            txtDescripcion.text = producto.descripcion
            txtCategoria.text = producto.categoria
            // Si no existe el restaurante mostramos un mensaje
            txtRestaurante.text =
                restaurante?.nombre ?: "Restaurante desconocido"

        }
    }

    /*
     * Método que se ejecuta al hacer clic en el botón de retroceso.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}