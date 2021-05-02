package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.model.product.factory.ProductFactory

class ColaDispenser: Dispenser {
    private var mColaList = ArrayList<Product>()


    override fun restock() {
        for (i in 1..10) {
            mColaList.add(ProductFactory.create<Cola>().get())
        }
    }

    override fun dispense(): Product {
        return mColaList.removeAt(0)
    }

    override fun getList(): ArrayList<Product> {
        return mColaList
    }
}