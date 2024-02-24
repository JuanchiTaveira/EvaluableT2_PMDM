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
    private val completeProductList: ArrayList<Product> = ArrayList()
    private var cart: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillProductList()

        productAdapter = ProductAdapter(completeProductList, this)
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

                productAdapter.filterProducts(category)
                
                updateCartCount()

                Snackbar.make(binding.root, category, Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        fillCategorySpinner()
    }

    private fun updateCartCount() {
        binding.tvCart.text = "(" + cart.size + ")"
    }

    fun fillCategorySpinner() {
        val categories = ArrayList<String>()

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "https://dummyjson.com/products/categories",
            null,
            { response ->

                categories.add("Todo")

                for (i in 0 until response.length()) {
                    categories.add(response.getString(i).capitalize())
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

    fun fillProductList() {
        val productRequest = JsonObjectRequest(
            Request.Method.GET, "https://dummyjson.com/products", null,
            { response ->
                val allProducts = response.getJSONArray("products")

                for (i in 0 until allProducts.length()) {
                    val actualProduct: JSONObject = allProducts.get(i) as JSONObject

                    val id: Int = actualProduct.getInt("id")
                    val imagesJson: JSONArray = actualProduct.getJSONArray("images")
                    val images: ArrayList<String> = ArrayList()

                    for (j in 0 until imagesJson.length()) {
                        images.add(imagesJson[j].toString())
                    }

                    val title: String = actualProduct.getString("title")
                    val price: Int = actualProduct.getInt("price")
                    val category: String = actualProduct.getString("category")

                    completeProductList.add(Product(id, images, title, price, category))
                }

            },
            null
        )

        Volley.newRequestQueue(applicationContext).add(productRequest)
    }
}