package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.DisplayConstants.PRICE
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.calculator.WalletCalculator
import com.example.vendingmachine.vendingcomponents.dispenser.DispenserProvider

class VendingMachine {

    private val mCalculator = WalletCalculator()
    private val mProvider = DispenserProvider()
    private var mDisplay = INSERT_COIN

    fun pressButton(product: Product) {
        val price = product.price
        val type = product.type
        mProvider.selectProduct(type)
        mCalculator.calculateChange(price)
        val canMakeChange = mCalculator.canMakeChange(mCalculator.mUserInputAmount - price)
        val isNotSoldOut = mProvider.getDispenser().getList().isNotEmpty()
        setMessage(product.price, canMakeChange)
        if (isNotSoldOut && canMakeChange) {
            mCalculator.updateCoins(mCalculator.mReturnMap)
            mProvider.getDispenser().getList().removeAt(0)
            mCalculator.returnCoins()
        }
    }

    fun getDisplay(): String {
        return mDisplay
    }

    private fun setMessage(price: Int, canMakeChange: Boolean) {
        mDisplay = if (!mCalculator.hasEnoughMoney(price)) priceDisplay(price)
        else if (mProvider.getDispenser().getList().isEmpty()) SOLD_OUT
        else if (canMakeChange) THANK_YOU
        else EXACT_CHANGE_ONLY
    }

    private fun priceDisplay(price: Int): String {
        return PRICE + " $" + String.format("%.2f", (price.toDouble() / 100.00))
    }

    fun insertCoin(coin: Int) {
        mCalculator.addCoin(coin)
        mDisplay = INSERT_COIN
    }

    fun returnCoins() {
        mCalculator.returnCoins()
        mDisplay = INSERT_COIN
    }

    fun getCurrentAmt(): String {
        return mCalculator.mUserInputAmount.toString()
    }
}