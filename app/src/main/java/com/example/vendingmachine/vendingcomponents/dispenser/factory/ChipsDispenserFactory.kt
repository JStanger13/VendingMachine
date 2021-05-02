package com.example.vendingmachine.vendingcomponents.dispenser.factory

import com.example.vendingmachine.vendingcomponents.dispenser.ChipsDispenser

class ChipsDispenserFactory: DispenserFactory() {
    override fun getDispenser() = ChipsDispenser()

}