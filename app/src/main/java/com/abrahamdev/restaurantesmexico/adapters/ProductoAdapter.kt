package com.abrahamdev.restaurantesmexico.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abrahamdev.restaurantesmexico.R
import com.abrahamdev.restaurantesmexico.data.Producto
/*
 * Adaptador para mostrar una lista de productos.
 */
class ProductoAdapter(
    productos: List<Producto>,
    private val onProductoClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
    // Lista original
    private val productosOriginales = productos.toList()
    // Lista que realmente mostraremos
    private var productos = productos.toList()
    /*
     * Clase que representa un ViewHolder para un producto.
     */
    class ProductoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            // Referencias a los elementos visuales del producto
        val nombre: TextView =
            itemView.findViewById(R.id.txtNombreProducto)
        val precio: TextView =
            itemView.findViewById(R.id.txtPrecioProducto)
        val descripcion: TextView =
            itemView.findViewById(R.id.txtDescripcionProducto)
    }

    /*
     * Método que se ejecuta al crear un ViewHolder.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductoViewHolder {
        // Inflamos el layout del producto con el ViewHolder
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(vista)
    }

    /*
     * Método que se ejecuta al vincular un ViewHolder con un producto.
     */
    override fun onBindViewHolder(
        holder: ProductoViewHolder,
        position: Int
    ) {
        // Obtenemos el producto en la posición dada del repositorio
        val producto = productos[position]
        // Asignamos los valores a los elementos visuales
        holder.nombre.text = producto.nombre
        holder.precio.text = "$${producto.precio}"
        holder.descripcion.text = producto.descripcion
        // Listener para cuando se hace clic en un producto
        holder.itemView.setOnClickListener {
            onProductoClick(producto)
        }
    }

    /*
     * Método que devuelve el número de productos en la lista.
     */
    override fun getItemCount(): Int {
        return productos.size
    }

    /*
     * Método para filtrar los productos al realizar una búsqueda.
     */
    fun filtrar(texto: String) {
        // Si no hay texto, mostramos todos los productos
        productos = if (texto.isBlank()) {
            productosOriginales
        }
        // Si hay texto, filtramos los productos
        else
        {
            productosOriginales.filter { producto ->
                producto.nombre.contains(texto, ignoreCase = true) ||
                        producto.descripcion.contains(texto, ignoreCase = true)
            }
        }
        // Notificamos al adaptador que los datos han cambiado
        notifyDataSetChanged()
    }
}