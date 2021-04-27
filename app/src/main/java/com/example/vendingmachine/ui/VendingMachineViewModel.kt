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

    fun pressCola() = mVendingMachine.selectProduct(Cola())
    fun pressChips() = mVendingMachine.selectProduct(Chips())
    fun pressCandy() = mVendingMachine.selectProduct(Candy())

    fun insertPenny() = mVendingMachine.insertCoin(1)
    fun insertNickle() = mVendingMachine.insertCoin(5)
    fun insertDime() = mVendingMachine.insertCoin(10)
    fun insertQuarter() = mVendingMachine.insertCoin(25)
    fun returnCoins() = mVendingMachine.returnCoins()

}