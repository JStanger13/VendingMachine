package com.example.vendingmachine.model.product

import com.example.vendingmachine.Display

class Cola(): Product() {
    override val price = 100
    override val type = Display.COLA
}