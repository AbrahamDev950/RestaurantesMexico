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

class ProductosFragment : Fragment() {

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

        val productos = ProductoRepository.obtenerProductos(
            restauranteId,
            categoria
        )

        recyclerProductos.layoutManager =
            LinearLayoutManager(requireContext())

        recyclerProductos.adapter =
            ProductoAdapter(productos) { producto ->

                // Más adelante abriremos aquí el detalle del producto.

            }
    }
}