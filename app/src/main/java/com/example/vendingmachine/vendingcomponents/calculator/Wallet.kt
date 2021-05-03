package com.example.vendingmachine.vendingcomponents.calculator

import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.QUARTER

abstract class Wallet {
    var mUserInputAmount = 0

    private var mNickels = 0
    private var mDimes = 0
    private var mQuarters = 0

    var mMap = mutableMapOf(
        NICKEL to mNickels,
        DIME to mDimes,
        QUARTER to mQuarters)

    var mReturnMap = mutableMapOf(
        NICKEL to 0,
        DIME to 0,
        QUARTER to 0)
}