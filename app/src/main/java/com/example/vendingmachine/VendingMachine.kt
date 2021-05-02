package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.CANDY
import com.example.vendingmachine.DisplayConstants.CHIPS
import com.example.vendingmachine.DisplayConstants.COLA
import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.DisplayConstants.PRICE
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.calculator.CoinValidator
import com.example.vendingmachine.vendingcomponents.dispenser.ColaDispenser
import com.example.vendingmachine.vendingcomponents.dispenser.Dispenser
import com.example.vendingmachine.vendingcomponents.dispenser.factory.DispenserFactory

class VendingMachine {

    private val mCalculator = CoinValidator()

    private var mColaDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    private var mChipsDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()
    private var mCandyDispenser = DispenserFactory.create<ColaDispenser>().getDispenser()

    private lateinit var mDispenser: Dispenser

    //Display
    private var mDisplay = INSERT_COIN

    init {
        mColaDispenser.restock()
        mChipsDispenser.restock()
        mCandyDispenser.restock()
    }

    fun pressButton(product: Product) {
        selectProduct(product.type)
        mCalculator.calculateChange(product.price)
        val canMakeChange = mCalculator.canMakeChange(mCalculator.mUserInputAmount - product.price)
        setMessage(product.price, canMakeChange)
        if(mDispenser.getList().isNotEmpty() && canMakeChange) {
            mCalculator.updateCoins(mCalculator.mReturnMap)
            mDispenser.getList().removeAt(0)
            mCalculator.returnCoins()
        }
    }

    //Display Methods
    fun getDisplay(): String {
        return mDisplay
    }

    private fun setMessage(price: Int, canMakeChange: Boolean) {
        mDisplay = if(!mCalculator.hasEnoughMoney(price)) priceDisplay(price)
        else if(mDispenser.getList().isEmpty()) SOLD_OUT
        else if(canMakeChange) THANK_YOU
        else EXACT_CHANGE_ONLY
    }

    private fun priceDisplay(price: Int): String {
        return PRICE + " $" + String.format("%.2f", (price.toDouble()/100.00))
    }

    //Wallet Methods
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

    private fun selectProduct(productType: String) {
        mDispenser = when(productType) {
            COLA -> mColaDispenser
            CHIPS -> mChipsDispenser
            CANDY -> mCandyDispenser
            else -> throw IllegalArgumentException()
        }
    }
}