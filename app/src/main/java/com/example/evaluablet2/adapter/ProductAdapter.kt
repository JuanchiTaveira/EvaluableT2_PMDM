package com.example.evaluablet2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluablet2.Product
import com.example.evaluablet2.R
import com.google.android.material.snackbar.Snackbar

class ProductAdapter(var lista: List<Product>, var context: Context): RecyclerView.Adapter<ProductAdapter.MyHolder>() {

    private var listaCompleta: List<Product> = lista

    class MyHolder(item: View): RecyclerView.ViewHolder(item) {

        var imagen: ImageView
        var titulo: TextView
        var precio: TextView
        var btnAniadir: Button

        init {
            imagen = item.findViewById(R.id.imagen_fila)
            titulo = item.findViewById(R.id.titulo_fila)
            precio = item.findViewById(R.id.precio_fila)
            btnAniadir = item.findViewById(R.id.btn_aniadir_fila)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = lista.get(position)

        holder.imagen.setImageResource(R.drawable.carrito_chico)
        holder.titulo.text = product.title
        holder.precio.text = product.price.toString() + " EUR"
    }

    fun filtrarProductos(categoria: String) {
        if (categoria.equals("Todo")) {
            lista = listaCompleta
        } else {
            lista = listaCompleta.filter { product -> product.category.equals(categoria, true) }.toList()
        }

        notifyDataSetChanged()
    }
}