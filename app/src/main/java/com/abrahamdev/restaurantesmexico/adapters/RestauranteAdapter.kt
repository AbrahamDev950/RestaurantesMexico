
package com.abrahamdev.restaurantesmexico.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.data.Restaurante
/*
 * Adaptador para mostrar una lista de restaurantes.
 */
class RestauranteAdapter(
    restaurantes: List<Restaurante>,
    private val onRestauranteClick: (Restaurante) -> Unit
) : RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    // La lista original
    private val restaurantesOriginales = restaurantes.toList()

    // Lista que realmente mostraremos
    private var restaurantes = restaurantes.toList()

    /*
     * Clase que representa un ViewHolder para un restaurante.
     */
    class RestauranteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            // Referencias a los elementos visuales del restaurante
        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val direccion: TextView = itemView.findViewById(R.id.txtDireccion)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
    }

    /*
     * Método que se ejecuta al crear un ViewHolder.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestauranteViewHolder {
        // Inflamos el layout del restaurante con el ViewHolder
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurante, parent, false)
        return RestauranteViewHolder(vista)
    }

    /*
     * Método que se ejecuta al vincular un ViewHolder con un restaurante.
     */
    override fun onBindViewHolder(
        holder: RestauranteViewHolder,
        position: Int
    ) {
        // Obtenemos el restaurante en la posición dada del repositorio
        val restaurante = restaurantes[position]
        // Asignamos los valores a los elementos visuales
        holder.nombre.text = restaurante.nombre
        holder.direccion.text = restaurante.direccion
        holder.descripcion.text = restaurante.descripcion
        // Listener para cuando se hace clic en un restaurante
        holder.itemView.setOnClickListener {
            onRestauranteClick(restaurante)
        }
    }

    /*
     * Método que devuelve el número de restaurantes en la lista.
     */
    override fun getItemCount(): Int {
        return restaurantes.size
    }

    /*
     * Método para filtrar los restaurantes al realizar una búsqueda.
     */
    fun filtrar(texto: String) {
        // Si no hay texto, mostramos todos los restaurantes
        restaurantes = if (texto.isBlank()) {
            restaurantesOriginales
        }
        // Si hay texto, filtramos los restaurantes
        else
        {
            restaurantesOriginales.filter { restaurante ->

                restaurante.nombre.contains(
                    texto,
                    ignoreCase = true
                ) || restaurante.direccion.contains(
                    texto,
                    ignoreCase = true
                )
            }
        }
        // Notificamos al adaptador que los datos han cambiado
        notifyDataSetChanged()
    }
}
