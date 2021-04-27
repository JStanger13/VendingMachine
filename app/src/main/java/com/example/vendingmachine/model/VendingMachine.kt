package com.example.vendingmachine.model

import com.example.vendingmachine.model.product.Product

class VendingMachine {
    fun selectProduct(product: Product) {}
    fun dispense() {}
    fun insertCoin(coin: Int) {}
    fun returnCoins() {}
}