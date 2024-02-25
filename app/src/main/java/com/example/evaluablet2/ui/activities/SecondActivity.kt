package com.example.evaluablet2.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluablet2.Product
import com.example.evaluablet2.adapter.CartAdapter
import com.example.evaluablet2.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity(), CartAdapter.OnRemoveFromCartListener {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var cart: ArrayList<Product>
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cart = intent.getSerializableExtra("cartProducts") as ArrayList<Product>
        binding.tvTotal.text = "Total: " + getTotal()

        cartAdapter = CartAdapter(cart, this)
        binding.reciclerProducts.adapter = cartAdapter
        binding.reciclerProducts.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

    }

    override fun removeFromCart(product: Product) {
        cart.remove(product)
        binding.tvTotal.text = "Total: " + getTotal()
    }

    private fun getTotal(): Int {
        var total = 0
        cart.forEach { product -> total += product.price }
        return total
    }
}