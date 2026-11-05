package com.abrahamdev.restaurantesmexico.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abrahamdev.restaurantesmexico.fragments.ProductosFragment
import com.abrahamdev.restaurantesmexico.fragments.ProductosFragment.Companion.nuevaInstancia

/*
 * Adaptador para el ViewPager de productos.
 */
class MenuPagerAdapter(
    activity: androidx.fragment.app.FragmentActivity,
    private val restauranteId: Int
) : FragmentStateAdapter(activity) {
    // Lista de categorías de productos
    private val categorias = listOf(
        "Comida",
        "Bebida",
        "Complemento"
    )

    /*
     * Método que devuelve el número de categorías.
     */
    override fun getItemCount(): Int {
        return categorias.size
    }

    /*
     * Método que devuelve un fragmento para cada categoría.
     */
    override fun createFragment(position: Int): Fragment {
        // Creamos un fragmento para cada categoría
        val categoria = categorias[position]
        // Devolvemos el fragmento con los productos de la categoría
        return nuevaInstancia(
            restauranteId,
            categoria
        )
    }
}