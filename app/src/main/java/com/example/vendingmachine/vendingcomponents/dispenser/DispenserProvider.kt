package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.DisplayConstants
import com.example.vendingmachine.vendingcomponents.dispenser.factory.DispenserFactory

class DispenserProvider {

    private var mColaDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    private var mChipsDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    private var mCandyDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    private lateinit var mDispenser: Dispenser

    init {
        mColaDispenser.restock()
        mChipsDispenser.restock()
        mCandyDispenser.restock()
    }

    fun getDispenser(): Dispenser {
        return mDispenser
    }

    fun selectProduct(productType: String) {
        mDispenser = when(productType) {
            DisplayConstants.COLA -> mColaDispenser
            DisplayConstants.CHIPS -> mChipsDispenser
            DisplayConstants.CANDY -> mCandyDispenser
            else -> throw IllegalArgumentException()
        }
    }
}