package com.example.vendingmachine.model.product.factory

import com.example.vendingmachine.model.product.Cola

class ColaFactory: ProductFactory() {
    override fun get() = Cola()
}