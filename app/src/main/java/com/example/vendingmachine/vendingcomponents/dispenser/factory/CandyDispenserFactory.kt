package com.example.vendingmachine.vendingcomponents.dispenser.factory

import com.example.vendingmachine.vendingcomponents.dispenser.CandyDispenser

class CandyDispenserFactory: DispenserFactory() {
    override fun getDispenser() = CandyDispenser()
}