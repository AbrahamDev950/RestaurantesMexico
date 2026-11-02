package com.abrahamdev.restaurantesmexico.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.data.Producto

class ProductoAdapter(
    private val productos: List<Producto>,
    private val onProductoClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val nombre: TextView =
            itemView.findViewById(R.id.txtNombreProducto)

        val precio: TextView =
            itemView.findViewById(R.id.txtPrecioProducto)

        val descripcion: TextView =
            itemView.findViewById(R.id.txtDescripcionProducto)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)

        return ProductoViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {

        val producto = productos[position]

        holder.nombre.text = producto.nombre
        holder.precio.text = "$${producto.precio}"
        holder.descripcion.text = producto.descripcion

        holder.itemView.setOnClickListener {
            onProductoClick(producto)
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}