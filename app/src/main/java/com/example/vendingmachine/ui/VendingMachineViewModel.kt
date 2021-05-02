package com.example.vendingmachine.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.QUARTER
import com.example.vendingmachine.VendingMachine
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola

class VendingMachineViewModel: ViewModel() {

    private val mVendingMachine = VendingMachine()

    var mDisplayText = ObservableField<String>()
    var mCoinAmtText = ObservableField<String>()

    init {
        setUpTexts()
    }

    //Buttons
    fun pressCola() {
        mVendingMachine.pressButton(Cola())
        setUpTexts()
    }
    fun pressChips() {
        mVendingMachine.pressButton(Chips())
        setUpTexts()
    }
    fun pressCandy() {
        mVendingMachine.pressButton(Candy())
        setUpTexts()
    }

    //Inserting Coins
    fun insertPenny() {
        mVendingMachine.insertCoin(1)
        setUpTexts()
    }
    fun insertNickle() {
        mVendingMachine.insertCoin(NICKEL)
        setUpTexts()
    }
    fun insertDime() {
        mVendingMachine.insertCoin(DIME)
        setUpTexts()
    }
    fun insertQuarter() {
        mVendingMachine.insertCoin(QUARTER)
        setUpTexts()
    }
    fun returnCoins() {
        mVendingMachine.returnCoins()
        setUpTexts()
    }
    private fun setUpTexts() {
        mDisplayText.set(mVendingMachine.getDisplay())
        mCoinAmtText.set(mVendingMachine.getCurrentAmt())
    }
}