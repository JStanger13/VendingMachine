package com.example.vendingmachine.vendingcomponents.dispenser.factory

import com.example.vendingmachine.vendingcomponents.dispenser.CandyDispenser
import com.example.vendingmachine.vendingcomponents.dispenser.ChipsDispenser
import com.example.vendingmachine.vendingcomponents.dispenser.ColaDispenser
import com.example.vendingmachine.vendingcomponents.dispenser.Dispenser

abstract class DispenserFactory {
    abstract fun getDispenser(): Dispenser

    companion object {
        inline fun <reified T : Dispenser> create(): DispenserFactory =
            when (T::class) {
                ColaDispenser::class -> ColaDispenserFactory()
                ChipsDispenser::class -> ChipsDispenserFactory()
                CandyDispenser::class -> CandyDispenserFactory()
                else -> throw IllegalArgumentException()
            }
    }
}