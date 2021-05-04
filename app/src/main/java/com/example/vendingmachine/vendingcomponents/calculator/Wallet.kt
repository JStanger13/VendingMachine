package com.example.vendingmachine.vendingcomponents.calculator

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.PENNY
import com.example.vendingmachine.DisplayConstants.QUARTER

abstract class Wallet {
    private var mCurrentAmount = 0

    private var mPennies = 0
    private var mNickels = 0
    private var mDimes = 0
    private var mQuarters = 0

    fun getCurrentAmount(): Int {
        return mCurrentAmount
    }
    fun setCurrentAmount(coins: Int) {
        mCurrentAmount = coins
    }

    var mMachineCoins = mutableMapOf(
        NICKEL to mNickels,
        DIME to mDimes,
        QUARTER to mQuarters)

    var mUserCoins = mutableMapOf(
        NICKEL to 0,
        DIME to 0,
        QUARTER to 0)
}