package com.example.evaluablet2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.evaluablet2.Product
import com.example.evaluablet2.adapter.ProductAdapter
import com.example.evaluablet2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private var listaTestProductos: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lista para testear el recyclerView
        listaTestProductos.add(Product(1, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Samsung Universe 9", 1249, "smartphones"));
        listaTestProductos.add(Product(2, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Samsung Universe 10", 999, "smartphones"));
        listaTestProductos.add(Product(3, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Samsung Universe 9", 1444, "smartphones"));

        productAdapter = ProductAdapter(listaTestProductos, this)
        binding.reciclerProductos.adapter = productAdapter
        binding.reciclerProductos.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

    }
}