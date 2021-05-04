package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.Display
import com.example.vendingmachine.vendingcomponents.calculator.WalletCalculator
import com.example.vendingmachine.vendingcomponents.dispenser.DispenserProvider

class VendingMachine {

    private val mCalculator = WalletCalculator()
    private val mProvider = DispenserProvider()
    private var mDisplay = Display()
    private var mCanMakeChange = false

    init {
        mDisplay.setCalculator(mCalculator)
        mDisplay.setProvider(mProvider)
    }

    fun getCurrentAmt(): String {
        return mDisplay.convertCentsToString(mCalculator.getCurrentAmount())
    }

    fun getDisplay(): Display {
        return mDisplay
    }

    fun pressButton(product: Product) {
        mDisplay.setExactChangeCheckText()
        mDisplay.resetReturnCoinsText()
        mProvider.selectProduct(product.type)
        mCalculator.calculateChange(product.price)
        mCanMakeChange = mCalculator.userMoneyCanMakeChange(
            mCalculator.getCurrentAmount() - product.price)
        mDisplay.setMessage(product, mCanMakeChange)
        purchaseSuccess()
    }

    fun insertCoin(coin: Int) {
        mCalculator.addCoin(coin)
        mDisplay.setExactChangeCheckText()
        mDisplay.resetReturnCoinsText()
    }

    fun returnCoins() {
        mCalculator.calculateChange(0)
        mCalculator.returnCoins()
        mDisplay.setDisplayMessage(INSERT_COIN)
        mDisplay.updateReturnCoinsString()
    }

    private fun purchaseSuccess() {
        if (!mProvider.getDispenser().isSoldOut() && mCanMakeChange) {
            mCalculator.updateCoins(mCalculator.mUserCoins)
            mProvider.getDispenser().dispense()
            mDisplay.updateReturnCoinsString()
            mCanMakeChange = mCalculator.machineMoneyCanMakeChange()
            mDisplay.checkIfExactChangeNeededDisplay()
        }
    }
}