package com.abrahamdev.restaurantesmexico.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.data.Restaurante

class RestauranteAdapter(
    private val restaurantes: List<Restaurante>,
    // Recibe una función lambda que se ejecutará cuando se haga clic en un restaurante
    private val onRestauranteClick: (Restaurante) -> Unit
) : RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    class RestauranteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val direccion: TextView = itemView.findViewById(R.id.txtDireccion)
        val descripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestauranteViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurante, parent, false)

        return RestauranteViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: RestauranteViewHolder,
        position: Int
    ) {

        val restaurante = restaurantes[position]

        holder.nombre.text = restaurante.nombre
        holder.direccion.text = restaurante.direccion
        holder.descripcion.text = restaurante.descripcion
        // El adapter se encarga de manejar el clic pero el activity lo maneja
        holder.itemView.setOnClickListener {
            onRestauranteClick(restaurante)
        }
    }

    override fun getItemCount(): Int {
        return restaurantes.size
    }
}
