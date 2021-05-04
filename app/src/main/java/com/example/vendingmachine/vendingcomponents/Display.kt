package com.example.vendingmachine.vendingcomponents

import com.example.vendingmachine.DisplayConstants
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.calculator.WalletCalculator
import com.example.vendingmachine.vendingcomponents.dispenser.DispenserProvider

class Display {

    private var mReturnCoins = ""
    private var mDisplayMessage = ""
    private var mChangeStateText = DisplayConstants.EXACT_CHANGE_ONLY
    private lateinit var mCalculator: WalletCalculator
    private lateinit var mProvider: DispenserProvider

    fun getReturnText(): String {
        return mReturnCoins
    }

    fun getDisplayMessage(): String {
        return mDisplayMessage
    }

    fun setDisplayMessage(message: String) {
        mDisplayMessage = message
    }

    fun setExactChangeCheckText() {
        mDisplayMessage = mChangeStateText
    }

    fun setCalculator(calculator: WalletCalculator) {
        mCalculator = calculator
    }
    fun setProvider(provider: DispenserProvider) {
        mProvider = provider
    }

    fun resetReturnCoinsText() {
        mReturnCoins = ""
    }

    fun updateReturnCoinsString() {
        val map = mCalculator.returnCoins()
        val quarters = map[DisplayConstants.QUARTER].toString() + " quarters "
        val dimes = map[DisplayConstants.DIME].toString() + " dimes "
        val nickels = map[DisplayConstants.NICKEL].toString() + " nickels"
        mReturnCoins = quarters + dimes + nickels
    }

    fun setMessage(product: Product, canMakeChange: Boolean) {
        mDisplayMessage = if (!mCalculator.hasEnoughMoney(product.price)) priceDisplay(product.price)
        else if (mProvider.getDispenser().getList().isEmpty()) "${DisplayConstants.SOLD_OUT}: ${product.type}"
        else if (canMakeChange) DisplayConstants.THANK_YOU
        else DisplayConstants.EXACT_CHANGE_ONLY
    }

    fun checkIfExactChangeNeededDisplay() {
        mChangeStateText = if(mCalculator.machineMoneyCanMakeChange()) DisplayConstants.INSERT_COIN else DisplayConstants.EXACT_CHANGE_ONLY
    }

    fun convertCentsToString(cents: Int): String {
        return " $" + String.format("%.2f", (cents.toDouble() / 100.00))
    }

    private fun priceDisplay(price: Int): String {
        return DisplayConstants.PRICE + convertCentsToString(price)
    }
}