package com.example.evaluablet2

import java.io.Serializable

class Product (
    var id: Int,
    var images: ArrayList<String>,
    var title: String,
    var price: Int,
    var category: String
) : Serializable {
}