package com.example.vendingmachine.vendingcomponents.calculator

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.QUARTER

class CoinValidator: WalletCalculator() {

    fun canMakeChange(changeAmount: Int): Boolean {
        return ((mReturnMap[QUARTER]!! * QUARTER)
                + (mReturnMap[DIME]!! * DIME)
                + (mReturnMap[NICKEL]!! * NICKEL)) == changeAmount
    }

    fun hasEnoughMoney(price: Int): Boolean {
        return mUserInputAmount >= price
    }
}