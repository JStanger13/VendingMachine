package com.example.vendingmachine.model.product

import com.example.vendingmachine.Display

class Candy: Product() {
    override val price = 65
    override val type = Display.CANDY
}