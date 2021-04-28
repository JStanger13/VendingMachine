package com.example.vendingmachine.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.vendingmachine.model.VendingMachine
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola

class VendingMachineViewModel: ViewModel() {

    private val mVendingMachine = VendingMachine()

    var mDisplayText = ObservableField<String>()
    var mCoinAmtText = ObservableField<String>()

    init {
        mDisplayText.set("WELCOME")
        mCoinAmtText.set("0")
    }

    //Buttons
    fun pressCola() {
        mDisplayText.set(mVendingMachine.pressButton(Cola()))
        mCoinAmtText.set(mVendingMachine.mCurrentAmount.toString())
    }
    fun pressChips() {
        mDisplayText.set(mVendingMachine.pressButton(Chips()))
        mCoinAmtText.set(mVendingMachine.mCurrentAmount.toString())
    }
    fun pressCandy() {
        mDisplayText.set(mVendingMachine.pressButton(Candy()))
        mCoinAmtText.set(mVendingMachine.mCurrentAmount.toString())
    }

    //Inserting Coins
    fun insertPenny() = mCoinAmtText.set(mVendingMachine.insertCoin(1))
    fun insertNickle() = mCoinAmtText.set(mVendingMachine.insertCoin(5))
    fun insertDime() = mCoinAmtText.set(mVendingMachine.insertCoin(10))
    fun insertQuarter() = mCoinAmtText.set(mVendingMachine.insertCoin(25))
    fun returnCoins() = mCoinAmtText.set(mVendingMachine.returnCoins())
}