package com.maxirucci.recyclerviewtp_its.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxirucci.recyclerviewtp_its.R
import com.maxirucci.recyclerviewtp_its.models.ProductoModel

class ProductosAdapter(
    private val context: Context,
    private val listaProductos: List<ProductoModel>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ProductosViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_productos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ProductosViewHolder -> holder.bind(listaProductos[position], position)
            else -> throw IllegalAccessException("No hay viewHolder para el bind")
        }
    }

    override fun getItemCount(): Int {
        return listaProductos.size
    }

    inner class ProductosViewHolder(itemView: View) : BaseViewHolder<ProductoModel>(itemView){
        @SuppressLint("SetTextI18n")
        override fun bind(item: ProductoModel, position: Int) {
            val txtNombre = itemView.findViewById<TextView>(R.id.txtNombre)
            val txtPrecio = itemView.findViewById<TextView>(R.id.txtPrecio)
            val txtDescripcion = itemView.findViewById<TextView>(R.id.txtDescripcion)
            val lbOferta = itemView.findViewById<TextView>(R.id.lbOferta)
            val imgPrducto = itemView.findViewById<ImageView>(R.id.imgProducto)

            txtNombre.text = item.nombre
            txtPrecio.text ="$" + item.precio.toString()
            txtDescripcion.text = item.descripcion
            lbOferta.isVisible = item.isOferta!!

            Glide.with(context).load(item.urlImagen).into(imgPrducto)
        }
    }
}