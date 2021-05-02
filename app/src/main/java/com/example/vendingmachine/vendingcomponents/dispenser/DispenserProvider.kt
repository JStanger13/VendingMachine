package com.example.vendingmachine.vendingcomponents.dispenser

import com.example.vendingmachine.vendingcomponents.dispenser.factory.DispenserFactory

class DispenserProvider {
    var mColaDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    var mChipsDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    var mCandyDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()

    init {
        mColaDispenser.restock()
        mChipsDispenser.restock()
        mCandyDispenser.restock()
    }
}