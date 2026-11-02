package com.abrahamdev.restaurantesmexico.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abrahamdev.restaurantesmexico.fragments.ProductosFragment

class MenuPagerAdapter(
    activity: androidx.fragment.app.FragmentActivity,
    private val restauranteId: Int
) : FragmentStateAdapter(activity) {

    private val categorias = listOf(
        "Comida",
        "Bebida",
        "Complemento"
    )

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun createFragment(position: Int): Fragment {

        val categoria = categorias[position]

        return ProductosFragment.nuevaInstancia(
            restauranteId,
            categoria
        )
    }
}