package com.example.vendingmachine.vendingcomponents.dispenser.factory

import com.example.vendingmachine.vendingcomponents.dispenser.ColaDispenser

class ColaDispenserFactory: DispenserFactory() {
    override fun getDispenser() = ColaDispenser()
}