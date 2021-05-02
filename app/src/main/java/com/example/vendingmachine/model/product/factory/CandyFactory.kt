package com.example.vendingmachine.model.product.factory

import com.example.vendingmachine.model.product.Candy

class CandyFactory: ProductFactory() {
    override fun get() = Candy()
}