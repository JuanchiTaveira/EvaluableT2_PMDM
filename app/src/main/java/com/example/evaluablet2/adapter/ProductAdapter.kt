package com.example.evaluablet2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.evaluablet2.Product
import com.example.evaluablet2.R

class ProductAdapter(var completeProductList: List<Product>, var context: Context): RecyclerView.Adapter<ProductAdapter.MyHolder>() {

    private var filteredList: List<Product> = completeProductList
    private var listener: OnBtnAddToCartListener

    init {
        listener = context as OnBtnAddToCartListener
    }

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

        Glide.with(context).load(product.images[0]).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString() + " EUR"

        holder.btnAddToCart.setOnClickListener {
            listener.addToCart(product)
        }
    }

    fun filterProducts(category: String) {
        if (category.equals("Todo")) {
            filteredList = completeProductList
        } else {
            filteredList = completeProductList.filter { product -> product.category.equals(category, true) }.toList()
        }

        notifyDataSetChanged()
    }

    interface OnBtnAddToCartListener {
        fun addToCart(product: Product)
    }
}