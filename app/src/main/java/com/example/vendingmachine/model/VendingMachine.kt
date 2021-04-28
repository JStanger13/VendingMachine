package com.example.vendingmachine.model

import com.example.vendingmachine.Display.CANDY
import com.example.vendingmachine.Display.CHIPS
import com.example.vendingmachine.Display.COLA
import com.example.vendingmachine.Display.PRICE
import com.example.vendingmachine.Display.SOLD_OUT
import com.example.vendingmachine.Display.THANK_YOU
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product

class VendingMachine {

    //Inventory
    private val mColaList = ArrayList<Cola>()
    private val mChipsList = ArrayList<Chips>()
    private val mCandyList =  ArrayList<Candy>()
    lateinit var mList: ArrayList<Product>

    //Coins
    private var mNickels = 0
    private var mDimes = 0
    private var mQuarters = 0
    var mCurrentAmount = 0

    init {
        restockInventory()
    }

    fun pressButton(product: Product): String {
        selectProduct(product)
        var display = ""

        if(mCurrentAmount < product.price ) {
            display = priceDisplay(product)
        }

        if(mCurrentAmount >= product.price){
            display = if(mList.isEmpty()) {
                SOLD_OUT
            } else {
                dispense()
                mCurrentAmount -= product.price
                THANK_YOU
            }
        }
       return display
    }

    fun convertCentsToDollars(cents: Int): String {
        return String.format("%.2f", (cents.toDouble()/100.00))
    }

    private fun priceDisplay(product: Product): String {
        return PRICE + " $" + convertCentsToDollars(product.price)
    }

    private fun selectProduct(product: Product) {
        mList = when(product.type) {
            COLA -> {
                mColaList as ArrayList<Product>
            }
            CHIPS -> {
                mChipsList as ArrayList<Product>
            }
            CANDY -> {
                mCandyList as ArrayList<Product>
            }
            else -> arrayListOf()
        }
    }

    private fun dispense() {
        mList.removeAt(0)
    }

    private fun checkValidity(coin: Int): Boolean {
        return when (coin) {
            5, 10, 25 -> true
            else -> false
        }
    }

    fun canMakeChange(product: Product): Boolean {
        val changeAmount = mCurrentAmount - product.price

        val returnQuarters = mQuarters - checkChange(25, mQuarters, changeAmount)
        var newChangeAmount = changeAmount - (returnQuarters * 25)
        val returnDimes = mDimes - checkChange(10, mDimes, changeAmount)
        newChangeAmount -= (returnDimes * 10)
        val returnNickels = mNickels - checkChange(5, mDimes, changeAmount)
        newChangeAmount -= (returnNickels * 5)

        if((returnQuarters * 25) + (returnDimes * 10) + (returnNickels * 5) == changeAmount) {
            mQuarters -= returnQuarters
            mDimes -= returnDimes
            mNickels -= returnNickels
            mCurrentAmount -= changeAmount
            return true
        }
        return false
    }

    private fun checkChange(coinValue: Int, coinAmt: Int, changeAmount: Int): Int {
        if(coinAmt == 0) {return 0}
        if(coinValue <= changeAmount) {
            val coinsNeeded = kotlin.math.floor(changeAmount.toDouble() / coinValue.toDouble())
            return kotlin.math.floor(coinAmt - coinsNeeded).toInt()

        }
        return 0
    }

    private fun addCoin(coin: Int) {
        when (coin) {
            5 -> mNickels += 1
            10 -> mDimes += 1
            25 -> mQuarters += 1
        }
    }

    fun insertCoin(coin: Int): String {
        if(checkValidity(coin)) mCurrentAmount += coin
        addCoin(coin)
        return mCurrentAmount.toString()
    }

    fun returnCoins(): String {
        mCurrentAmount = 0
        return "0"
    }

    private fun restockInventory() {
        for (i in 1..10) {
            mColaList.add(Cola())
            mChipsList.add(Chips())
            mCandyList.add(Candy())
        }
    }
}