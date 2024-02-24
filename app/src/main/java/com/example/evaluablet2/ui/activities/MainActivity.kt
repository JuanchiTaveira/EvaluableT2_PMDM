package com.example.evaluablet2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.evaluablet2.Product
import com.example.evaluablet2.adapter.ProductAdapter
import com.example.evaluablet2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray
import org.json.JSONObject

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
        binding.reciclerProducts.adapter = productAdapter
        binding.reciclerProducts.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        binding.spinnerCategories.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val category = parent!!.adapter.getItem(position).toString()

                productAdapter.filtrarProductos(category)

                Snackbar.make(binding.root, category, Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        fillCategorySpinner()
    }

    fun fillCategorySpinner() {
        val categories = ArrayList<String>()

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://dummyjson.com/products/categories",
            null,
            Response.Listener<JSONArray> { response ->
                for (i in 0 until response.length()) {
                    categories.add(response.getString(i))
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    categories
                )
                binding.spinnerCategories.adapter = adapter
            },
            null
        )

        Volley.newRequestQueue(applicationContext).add(jsonArrayRequest)
    }
}