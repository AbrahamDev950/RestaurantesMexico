package com.abrahamdev.restaurantesmexico.fragments

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.abrahamdev.restaurantesmexico.data.Producto
import com.abrahamdev.restaurantesmexico.data.local.DatabaseProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.adapters.ProductoAdapter

class ProductosFragment : Fragment() {
    private lateinit var productoAdapter: ProductoAdapter
    companion object {

        private const val ARG_RESTAURANTE_ID = "restaurante_id"
        private const val ARG_CATEGORIA = "categoria"

        fun nuevaInstancia(
            restauranteId: Int,
            categoria: String
        ): ProductosFragment {

            val fragment = ProductosFragment()

            val argumentos = Bundle().apply {
                putInt(ARG_RESTAURANTE_ID, restauranteId)
                putString(ARG_CATEGORIA, categoria)
            }

            fragment.arguments = argumentos

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_productos,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val restauranteId = requireArguments()
            .getInt(ARG_RESTAURANTE_ID)

        val categoria = requireArguments()
            .getString(ARG_CATEGORIA) ?: ""

        val recyclerProductos = view.findViewById<RecyclerView>(
            R.id.recyclerProductos
        )

        recyclerProductos.layoutManager =
            LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {

            val database = DatabaseProvider.getDatabase(requireContext())

            val productos = withContext(Dispatchers.IO) {

                database.productoDao().obtenerProductos(
                    restauranteId,
                    categoria
                )
            }

            val productosModelo = productos.map { entidad ->
                Producto(
                    id = entidad.id,
                    restauranteId = entidad.restauranteId,
                    nombre = entidad.nombre,
                    precio = entidad.precio,
                    descripcion = entidad.descripcion,
                    categoria = entidad.categoria
                )
            }

            productoAdapter = ProductoAdapter(productosModelo) { producto ->

                val intent = android.content.Intent(
                    requireContext(),
                    com.abrahamdev.restaurantesmexico.DetalleProductoActivity::class.java
                )

                intent.putExtra("PRODUCTO_ID", producto.id)

                startActivity(intent)
            }

            recyclerProductos.adapter = productoAdapter
        }
//        val productos = ProductoRepository.obtenerProductos(
//            restauranteId,
//            categoria
//        )
//
//        recyclerProductos.layoutManager =
//            LinearLayoutManager(requireContext())
//
//        productoAdapter =
//            ProductoAdapter(productos) { producto ->
//
//                val intent = android.content.Intent(
//                    requireContext(),
//                    com.abrahamdev.restaurantesmexico.DetalleProductoActivity::class.java
//                )
//
//                intent.putExtra("PRODUCTO_ID", producto.id)
//
//                startActivity(intent)
//            }
//        recyclerProductos.adapter = productoAdapter
    }
    fun filtrarProductos(texto: String) {
        if (::productoAdapter.isInitialized) {
            productoAdapter.filtrar(texto)
        }
    }
}