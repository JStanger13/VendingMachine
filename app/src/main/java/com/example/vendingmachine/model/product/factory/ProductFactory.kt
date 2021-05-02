package com.example.vendingmachine.model.product.factory

import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.dispenser.CandyDispenser
import com.example.vendingmachine.vendingcomponents.dispenser.ChipsDispenser

abstract class ProductFactory {
    abstract fun get(): Product

    companion object {
        inline fun <reified T : Product> create(): ProductFactory =
            when (T::class) {
                Cola::class -> ColaFactory()
                ChipsDispenser::class -> ChipsFactory()
                CandyDispenser::class -> CandyFactory()
                else -> throw IllegalArgumentException()
            }
    }
}