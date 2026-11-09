package com.abrahamdev.restaurantesmexico

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.abrahamdev.restaurantesmexico.data.local.DatabaseProvider

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
class DetalleProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

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
        lifecycleScope.launch {
            // Obtenemos la base de datos
            val database = DatabaseProvider.getDatabase(this@DetalleProductoActivity)
            // Obtenemos el producto y el restaurante
            val (producto, restaurante) = withContext(Dispatchers.IO) {

                // Obtenemos el producto de la base de datos por su ID
                val producto = database.productoDao()
                    .obtenerProductoPorId(productoId)

                // Obtenemos el restaurante de la base de datos por su ID
                val restaurante = producto?.let {
                    database.restauranteDao()
                        .obtenerPorId(it.restauranteId)
                }

                // Devolvemos el producto y el restaurante
                producto to restaurante
            }

            // Si el producto no es nulo, buscamos los elementos de la vista
            if (producto != null) {
                val txtNombre = findViewById<TextView>(R.id.txtDetalleNombre)
                val txtPrecio = findViewById<TextView>(R.id.txtDetallePrecio)
                val txtDescripcion = findViewById<TextView>(R.id.txtDetalleDescripcion)
                val txtCategoria = findViewById<TextView>(R.id.txtDetalleCategoria)
                val txtRestaurante = findViewById<TextView>(R.id.txtDetalleRestaurante)

                // Mostramos los detalles del producto
                txtNombre.text = producto.nombre
                txtPrecio.text = "$${"%.2f".format(producto.precio)}"
                txtDescripcion.text = producto.descripcion
                txtCategoria.text = producto.categoria
                txtRestaurante.text = restaurante?.nombre ?: "Restaurante desconocido"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}