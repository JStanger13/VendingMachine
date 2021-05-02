package com.example.vendingmachine.model.product.factory

import com.example.vendingmachine.model.product.Chips

class ChipsFactory: ProductFactory() {
    override fun get() = Chips()
}