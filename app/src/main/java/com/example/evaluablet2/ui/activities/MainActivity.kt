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
import com.google.android.material.snackbar.Snackbar
import java.util.function.Predicate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val listaTestProductos: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // lista para testear el recyclerView
        listaTestProductos.add(Product(1, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Samsung Universe 9", 1249, "Informatica"));
        listaTestProductos.add(Product(2, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Microondas Sony", 999, "Cocina"));
        listaTestProductos.add(Product(3, listOf("https://cdn.dummyjson.com/product-images/1/1.jpg"), "Cama matrimonial", 1444, "Muebles"));

        productAdapter = ProductAdapter(listaTestProductos, this)
        binding.reciclerProductos.adapter = productAdapter
        binding.reciclerProductos.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        binding.spinnerCategoria.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val categoria = parent!!.adapter.getItem(position).toString()

                productAdapter.filtrarProductos(categoria)

                Snackbar.make(binding.root, categoria, Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }
}