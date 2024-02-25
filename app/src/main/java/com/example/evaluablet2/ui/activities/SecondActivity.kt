package com.example.evaluablet2.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.evaluablet2.Product
import com.example.evaluablet2.adapter.CartAdapter
import com.example.evaluablet2.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondActivity: AppCompatActivity(), CartAdapter.OnRemoveFromCartListener {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var cart: ArrayList<Product>
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cart = intent.getSerializableExtra("cartProducts") as ArrayList<Product>
        setTvTotal()

        cartAdapter = CartAdapter(cart, this)
        binding.reciclerProducts.adapter = cartAdapter
        binding.reciclerProducts.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.btnClearCart.setOnClickListener {
            cart.removeAll { true }
            cartAdapter.notifyDataSetChanged()
            setTvTotal()
            Snackbar.make(binding.root, "Carrito vaciado", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnBuy.setOnClickListener {
            Snackbar.make(binding.root, "Enhorabuena, compra por valor de ${setTvTotal()} EUR realizada", Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun removeFromCart(position: Int) {
        cart.removeAt(position)
        setTvTotal()
    }

    private fun setTvTotal(): Int {
        var total = 0
        cart.forEach { product -> total += product.price }
        binding.tvTotal.text = "Total: ${total} EUR"
        return total
    }
}