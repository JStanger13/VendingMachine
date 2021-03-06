package com.example.vendingmachine.ui

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.vendingmachine.DisplayConstants.DIME
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.PENNY
import com.example.vendingmachine.DisplayConstants.QUARTER
import com.example.vendingmachine.R
import com.example.vendingmachine.VendingMachine
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.factory.ProductFactory

class VendingMachineViewModel: ViewModel() {

    var mDisplayText = ObservableField<String>()
    var mCoinAmtText = ObservableField<String>()
    var mReturnCoinsText = ObservableField<String>()

    private val mCola = ProductFactory.create<Cola>().get()
    private val mChips = ProductFactory.create<Chips>().get()
    private val mCandy = ProductFactory.create<Candy>().get()

    private val mVendingMachine = VendingMachine()

    init {
        updateTexts()
    }

    fun pressButton(view: View) {
        when(view.id) {

            //Product Buttons
            R.id.cola -> mVendingMachine.pressButton(mCola)
            R.id.chips -> mVendingMachine.pressButton(mChips)
            R.id.candy -> mVendingMachine.pressButton(mCandy)

            //Coin Buttons
            R.id.penny -> mVendingMachine.insertCoin(PENNY)
            R.id.nickel -> mVendingMachine.insertCoin(NICKEL)
            R.id.dime -> mVendingMachine.insertCoin(DIME)
            R.id.quarter -> mVendingMachine.insertCoin(QUARTER)

            R.id.returncoins -> mVendingMachine.returnCoins()
        }
        updateTexts()
    }

    private fun updateTexts() {
        mDisplayText.set(mVendingMachine.getDisplay().getDisplayMessage())
        mCoinAmtText.set(mVendingMachine.getCurrentAmt())
        mReturnCoinsText.set(mVendingMachine.getDisplay().getReturnText())
    }
}