package com.abrahamdev.restaurantesmexico.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.adapters.ProductoAdapter
import com.abrahamdev.restaurantesmexico.data.ProductoRepository
/*
 * Clase que representa el fragmento de productos para usarse en el ViewPager.
 */
class ProductosFragment : Fragment() {
    // Inyectamos el adaptador para mostrar los productos
    private lateinit var productoAdapter: ProductoAdapter

    // Constantes para el ID del restaurante y la categoría
    companion object {
        private const val RESTAURANTE_ID = "restaurante_id"
        private const val CATEGORIA = "categoria"

        // Método para crear una nueva instancia del fragmento
        // con el ID del restaurante y la categoría
        fun nuevaInstancia(
            restauranteId: Int,
            categoria: String
        ): ProductosFragment {
            // Creamos el fragmento
            val fragment = ProductosFragment()
            // Creamos los argumentos para el fragmento con el ID del restaurante y la categoría
            val argumentos = Bundle().apply {
                putInt(RESTAURANTE_ID, restauranteId)
                putString(CATEGORIA, categoria)
            }
            // Añadimos los argumentos al fragmento
            fragment.arguments = argumentos

            return fragment
        }
    }

    /*
     * Método que se ejecuta al crear la vista del fragmento.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflamos el layout del fragmento
        return inflater.inflate(
            R.layout.fragment_productos,
            container,
            false
        )
    }

    /*
     * Método que se ejecuta al crear la vista del fragmento.
     */
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos el ID del restaurante y la categoría
        val restauranteId = requireArguments()
            .getInt(RESTAURANTE_ID)

        val categoria = requireArguments()
            .getString(CATEGORIA) ?: ""

        // Referencia al RecyclerView para mostrar los productos
        val recyclerProductos = view.findViewById<RecyclerView>(
            R.id.recyclerProductos
        )
        // Obtenemos los productos desde el repositorio
        val productos = ProductoRepository.obtenerProductos(
            restauranteId,
            categoria
        )
        // Configuramos el RecyclerView con un LinearLayoutManager
        recyclerProductos.layoutManager =
            LinearLayoutManager(requireContext())
        // Configuramos el adaptador con los productos
        productoAdapter =
            ProductoAdapter(productos) { producto ->
                // Cuando se hace clic en un producto, abrimos la actividad de detalle
                val intent = android.content.Intent(
                    requireContext(),
                    com.abrahamdev.restaurantesmexico.DetalleProductoActivity::class.java
                )
                intent.putExtra("PRODUCTO_ID", producto.id)
                startActivity(intent)
            }
        // Añadimos el adaptador al RecyclerView
        recyclerProductos.adapter = productoAdapter
    }

    /*
     * Método para filtrar los productos.
     */
    fun filtrarProductos(texto: String) {
        if (::productoAdapter.isInitialized) {
            productoAdapter.filtrar(texto)
        }
    }
}