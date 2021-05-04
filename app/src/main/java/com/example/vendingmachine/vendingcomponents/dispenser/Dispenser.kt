package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.model.product.Product

interface Dispenser {
    fun restock()
    fun dispense(): Product
    fun getList(): ArrayList<Product>
    fun isSoldOut(): Boolean
}