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

            val database = DatabaseProvider.getDatabase(this@DetalleProductoActivity)

            val (producto, restaurante) = withContext(Dispatchers.IO) {

                val producto = database.productoDao()
                    .obtenerProductoPorId(productoId)

                val restaurante = producto?.let {
                    database.restauranteDao()
                        .obtenerPorId(it.restauranteId)
                }

                producto to restaurante
            }

            if (producto != null) {
                val txtNombre = findViewById<TextView>(R.id.txtDetalleNombre)
                val txtPrecio = findViewById<TextView>(R.id.txtDetallePrecio)
                val txtDescripcion = findViewById<TextView>(R.id.txtDetalleDescripcion)
                val txtCategoria = findViewById<TextView>(R.id.txtDetalleCategoria)
                val txtRestaurante = findViewById<TextView>(R.id.txtDetalleRestaurante)

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