package com.example.vendingmachine.vendingcomponents.calculator

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.PENNY
import com.example.vendingmachine.DisplayConstants.QUARTER
import java.lang.StrictMath.abs

class WalletCalculator: Wallet() {

    fun returnCoins(): MutableMap<Int, Int> {
        mUserInputAmount = 0
        return mUserCoins
    }

    fun addCoin(coin: Int) {
        mMachineCoins[coin] = (mMachineCoins[coin] ?: error("")) + 1
        mUserInputAmount += coin
    }

    fun calculateChange(price: Int) {
        returnCoinAmount(NICKEL, mMachineCoins[NICKEL]!!,
            returnCoinAmount(DIME, mMachineCoins[DIME]!!,
                returnCoinAmount(QUARTER,
                    mMachineCoins[QUARTER]!!,
                    abs(mUserInputAmount - price))))
    }

    private fun returnCoinAmount(coinValue: Int, coinAmt: Int, changeAmount: Int): Int {

        var newCoinAmt = 0
        if(coinValue <= changeAmount && coinAmt > 0) {
            newCoinAmt = abs(changeAmount.toDouble() / coinValue.toDouble()).toInt()
        }
        mUserCoins[coinValue] = newCoinAmt
        return changeAmount - (newCoinAmt  * coinValue)
    }

    fun updateCoins(map: MutableMap<Int, Int>) {
        mMachineCoins[NICKEL]!!.minus(map[NICKEL]!!)
        mMachineCoins[DIME]!!.minus(map[DIME]!!)
        mMachineCoins[QUARTER]!!.minus(map[QUARTER]!!)
    }

    fun canMakeChange(changeAmount: Int): Boolean {
        return ((mUserCoins[QUARTER]!! * QUARTER)
                + (mUserCoins[DIME]!! * DIME)
                + (mUserCoins[NICKEL]!! * NICKEL)) == changeAmount
    }

    fun validateChange(): Boolean {
        return (mUserCoins[QUARTER]!! <= mMachineCoins[QUARTER]!!)
                && (mUserCoins[DIME]!! <= mMachineCoins[DIME]!!)
                && (mUserCoins[NICKEL]!! <= mMachineCoins[NICKEL]!!)
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