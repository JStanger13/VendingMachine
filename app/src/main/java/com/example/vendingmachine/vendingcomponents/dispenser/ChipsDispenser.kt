package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.model.product.factory.ProductFactory

class ChipsDispenser: Dispenser {
    private var mChipsList = ArrayList<Product>()

    override fun restock() {
        for (i in 1..10) {
            mChipsList.add(ProductFactory.create<Chips>().get())
        }
    }

    override fun dispense(): Product {
        return mChipsList.removeAt(0)
    }

    override fun getList(): ArrayList<Product> {
        return mChipsList
    }
}