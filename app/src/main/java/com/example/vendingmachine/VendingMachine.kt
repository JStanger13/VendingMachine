package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.PRICE
import com.example.vendingmachine.DisplayConstants.QUARTER
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.calculator.WalletCalculator
import com.example.vendingmachine.vendingcomponents.dispenser.DispenserProvider
import java.lang.Math.floor
import java.lang.StrictMath.abs

class VendingMachine {

    private val mCalculator = WalletCalculator()
    private val mProvider = DispenserProvider()
    private var mDisplay = EXACT_CHANGE_ONLY
    private var mReturnCoins = ""
    private var mCanMakeChange = false
    private var mType = ""
    private var mPrice = 0
    private var mChangeStateText = EXACT_CHANGE_ONLY

    fun pressButton(product: Product) {
        mReturnCoins = ""
        mPrice = product.price
        mType = product.type
        mProvider.selectProduct(mType)
        mCalculator.calculateChange(mPrice)
        mCanMakeChange = mCalculator.canMakeChange(mCalculator.mUserInputAmount - mPrice)
        val isNotSoldOut = mProvider.getDispenser().getList().isNotEmpty()
        setMessage()
        if (isNotSoldOut && mCanMakeChange) {
            mCalculator.updateCoins(mCalculator.mUserCoins)
            mProvider.getDispenser().getList().removeAt(0)
            updateReturnCoinsString()
            resetDisplay()
        }
    }

    private fun setCanMakeChange() {
        mCalculator.calculateChange(100)
        val validateChangeForCola = mCalculator.validateChange()
        mCalculator.calculateChange(50)
        val validateChangeForChips = mCalculator.validateChange()
        mCalculator.calculateChange(65)
        val validateChangeForCandy = mCalculator.validateChange()

        mCanMakeChange = validateChangeForCola && validateChangeForChips && validateChangeForCandy
    }

    fun getDisplay(): String {
        return mDisplay
    }

    fun insertCoin(coin: Int) {
        if (mCalculator.isCoinValid(coin)) mCalculator.addCoin(coin)
            mDisplay = mChangeStateText
            mReturnCoins = ""
    }

    private fun resetDisplay() {
        setCanMakeChange()
        mChangeStateText = if(mCanMakeChange) INSERT_COIN else EXACT_CHANGE_ONLY
        mDisplay = mChangeStateText
    }

    fun returnCoins() {
        mCalculator.calculateChange(0)
        mCalculator.returnCoins()
        mDisplay = INSERT_COIN
        updateReturnCoinsString()
    }

    fun getCurrentAmt(): String {
        return convertCentsToString(mCalculator.mUserInputAmount)
    }

    fun getReturnText(): String {
        return mReturnCoins
    }

    private fun updateReturnCoinsString() {
        var map = mCalculator.returnCoins()
        val quarters = map[QUARTER].toString() + " quarters "
        val dimes = map[DIME].toString() + " dimes "
        val nickels = map[NICKEL].toString() + " nickels"
        mReturnCoins = quarters + dimes + nickels
    }

    private fun setMessage() {
        mDisplay = if (!mCalculator.hasEnoughMoney(mPrice)) priceDisplay(mPrice)
        else if (mProvider.getDispenser().getList().isEmpty()) "$SOLD_OUT: $mType"
        else if (mCanMakeChange) THANK_YOU
        else EXACT_CHANGE_ONLY
    }

    private fun convertCentsToString(cents: Int): String {
        return " $" + String.format("%.2f", (cents.toDouble() / 100.00))
    }

    private fun priceDisplay(price: Int): String {
        return PRICE + convertCentsToString(price)
    }
}