package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.CANDY
import com.example.vendingmachine.DisplayConstants.CHIPS
import com.example.vendingmachine.DisplayConstants.COLA
import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.DisplayConstants.PRICE
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import com.example.vendingmachine.vendingcomponents.calculator.CoinValidator

class VendingMachine {

    private val mCalculator = CoinValidator()

    //Inventory
    private val mColaList = ArrayList<Cola>()
    private val mChipsList = ArrayList<Chips>()
    private val mCandyList =  ArrayList<Candy>()
    private lateinit var mList: ArrayList<Product>

    //Display
    private var mDisplay = INSERT_COIN

    init {
        restockInventory()
    }

    fun pressButton(product: Product) {
        selectProduct(product.type)
        mCalculator.calculateChange(product.price)
        val canMakeChange = mCalculator.canMakeChange(
            mCalculator.mUserInputAmount - product.price)
        setMessage(product.price, canMakeChange)

        if(mList.isNotEmpty() && canMakeChange) {
            mCalculator.updateCoins(mCalculator.mReturnMap)
            mList.removeAt(0)
            mCalculator.returnCoins()
        }

    }

    //Display Methods
    fun getDisplay(): String {
        return mDisplay
    }

    private fun setMessage(price: Int, canMakeChange: Boolean) {
        mDisplay = if(!mCalculator.hasEnoughMoney(price)) priceDisplay(price)
        else if(mList.isEmpty()) SOLD_OUT
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

    //Inventory Methods
    private fun restockInventory() {
        for (i in 1..10) {
            mColaList.add(Cola())
            mChipsList.add(Chips())
            mCandyList.add(Candy())
        }
    }

    private fun selectProduct(productType: String) {
        mList = when(productType) {
            COLA ->  mColaList as ArrayList<Product>
            CHIPS ->  mChipsList as ArrayList<Product>
            CANDY ->  mCandyList as ArrayList<Product>
            else -> arrayListOf()
        }
    }
}