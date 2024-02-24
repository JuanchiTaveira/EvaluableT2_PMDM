package com.example.evaluablet2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.example.evaluablet2.Product
import com.example.evaluablet2.R

class ProductAdapter(var filteredList: List<Product>, var context: Context): RecyclerView.Adapter<ProductAdapter.MyHolder>() {

    private var completeList: List<Product> = filteredList

    class MyHolder(item: View): RecyclerView.ViewHolder(item) {

        var image: ImageView
        var title: TextView
        var price: TextView
        var btnAddToCart: Button

        init {
            image = item.findViewById(R.id.row_image)
            title = item.findViewById(R.id.row_title)
            price = item.findViewById(R.id.row_price)
            btnAddToCart = item.findViewById(R.id.btn_add_to_cart)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = filteredList.get(position)

        holder.image.setImageResource(R.drawable.carrito_chico)
        holder.title.text = product.title
        holder.price.text = product.price.toString() + " EUR"
    }

    fun filterProducts(category: String) {
        if (category.equals("Todo")) {
            filteredList = completeList
        } else {
            filteredList = completeList.filter { product -> product.category.equals(category, true) }.toList()
        }

        notifyDataSetChanged()
    }
}