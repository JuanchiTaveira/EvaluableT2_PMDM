package com.example.evaluablet2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.evaluablet2.Product
import com.example.evaluablet2.R

class CartAdapter(var products: ArrayList<Product>, var context: Context) : RecyclerView.Adapter<CartAdapter.MyHolder>() {

    private lateinit var listener: OnRemoveFromCartListener

    init {
        listener = context as OnRemoveFromCartListener
    }

    class MyHolder(itemView: View) : ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var price: TextView
        var btnRemove: Button

        init {
            image = itemView.findViewById(R.id.cart_row_image)
            title = itemView.findViewById(R.id.cart_row_title)
            price = itemView.findViewById(R.id.cart_row_price)
            btnRemove = itemView.findViewById(R.id.btn_remove_from_cart)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = products.get(position)

        Glide.with(context).load(product.images[0]).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString() + " EUR"

        holder.btnRemove.setOnClickListener {
            listener.removeFromCart(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, products.size - position)
        }
    }

    interface OnRemoveFromCartListener {
        fun removeFromCart(position: Int)
    }

}