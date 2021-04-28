package com.example.vendingmachine.model.product

import com.example.vendingmachine.Display

class Chips: Product() {
    override val price = 50
    override val type = Display.CHIPS
}