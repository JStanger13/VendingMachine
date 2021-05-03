package com.example.vendingmachine.vendingcomponents.calculator

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.PENNY
import com.example.vendingmachine.DisplayConstants.QUARTER
import java.lang.StrictMath.abs

class WalletCalculator: Wallet() {

    fun returnCoins(): MutableMap<Int, Int> {
        mUserInputAmount = 0
        return mReturnMap
    }

    fun addCoin(coin: Int) {
        mMap[coin] = (mMap[coin] ?: error("")) + 1
        mUserInputAmount += coin
    }

    fun calculateChange(price: Int) {
        returnCoinAmount(NICKEL, mMap[NICKEL]!!,
            returnCoinAmount(DIME, mMap[DIME]!!,
                returnCoinAmount(QUARTER,
                    mMap[QUARTER]!!,
                    abs(mUserInputAmount - price))))
    }

    private fun returnCoinAmount(coinValue: Int, coinAmt: Int, changeAmount: Int): Int {

        var newCoinAmt = 0
        if(coinValue <= changeAmount && coinAmt > 0) {
            newCoinAmt = abs(changeAmount.toDouble() / coinValue.toDouble()).toInt()
        }
        mReturnMap[coinValue] = newCoinAmt
        return changeAmount - (newCoinAmt  * coinValue)
    }

    fun updateCoins(map: MutableMap<Int, Int>) {
        mMap[NICKEL]!!.minus(map[NICKEL]!!)
        mMap[DIME]!!.minus(map[DIME]!!)
        mMap[QUARTER]!!.minus(map[QUARTER]!!)
    }

    fun canMakeChange(changeAmount: Int): Boolean {
        return ((mMap[QUARTER]!! * QUARTER)
                + (mMap[DIME]!! * DIME)
                + (mMap[NICKEL]!! * NICKEL)) >= changeAmount
    }

    fun hasEnoughMoney(price: Int): Boolean {
        return mUserInputAmount >= price
    }

    fun isCoinValid(coin: Int): Boolean {
        return when(coin) {
            NICKEL, DIME, QUARTER -> true
            PENNY -> false
            else -> throw IllegalArgumentException()
        }
    }
}