package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.model.product.factory.ProductFactory

class CandyDispenser: Dispenser {
    private var mCandyList = ArrayList<Product>()


    override fun restock() {
        for (i in 1..10) {
            mCandyList.add(ProductFactory.create<Candy>().get())
        }
    }

    override fun dispense(): Product {
        return mCandyList.removeAt(0)
    }

    override fun getList(): ArrayList<Product> {
        return mCandyList
    }
}