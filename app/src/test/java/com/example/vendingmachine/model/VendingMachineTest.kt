package com.example.vendingmachine.model

import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.QUARTER
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.VendingMachine
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import org.junit.Before
import org.junit.Test

class VendingMachineTest {

    private lateinit var mCola: Cola
    private lateinit var mChips: Chips
    private lateinit var mCandy: Candy
    private lateinit var mVendingMachine: VendingMachine

    @Before
    fun setup() {
        mVendingMachine = VendingMachine()
        mCola = Cola()
        mChips = Chips()
        mCandy = Candy()
    }

    @Test
    fun testPressButton_DisplaysSoldOut() {
        //Cola
        buyOutProduct(mCola, QUARTER, 4)
        insertEnoughCoins(QUARTER, 4)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay() == SOLD_OUT)

        //Chips
        buyOutProduct(mChips, QUARTER, 2)
        insertEnoughCoins(QUARTER, 2)
        mVendingMachine.pressButton(mChips)
        assert(mVendingMachine.getDisplay() == SOLD_OUT)

        //Candy
        buyOutProduct(this.mCandy, NICKEL, 13)
        insertEnoughCoins(NICKEL, 13)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay() == SOLD_OUT)

    }

    @Test
    fun testPressButton_DisplaysExactChangeOnly() {
        insertEnoughCoins(QUARTER, 3)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay() == EXACT_CHANGE_ONLY)
    }

    @Test
    fun testPressButton_DisplaysThankYou() {
        insertEnoughCoins(QUARTER, 4)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay() == THANK_YOU)
    }

    @Test
    fun testPressButton_DisplaysPrice() {
        //Cola
        insertEnoughCoins(QUARTER, 1)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay() == "PRICE $1.00")

        //Chips
        mVendingMachine.pressButton(mChips)
        assert(mVendingMachine.getDisplay() == "PRICE $0.50")

        //Candy
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay() == "PRICE $0.65")
    }

    @Test
    fun testPressButton_CanMakeChange() {
        insertEnoughCoins(QUARTER, 5)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay() == THANK_YOU)
    }

    @Test
    fun testReturnCoins_displaysZeroCents() {
        assert(mVendingMachine.getCurrentAmt() == "0")
        mVendingMachine.insertCoin(QUARTER)
        assert(mVendingMachine.getCurrentAmt() == "25")
        mVendingMachine.returnCoins()
        assert(mVendingMachine.getCurrentAmt() == "0")
    }

    private fun buyOutProduct(product: Product, coin: Int, amt: Int) {
        for (i in 1..10) {
            insertEnoughCoins(coin, amt)
            mVendingMachine.pressButton(product)
        }
    }

    private fun insertEnoughCoins(coin: Int, amt: Int) {
        for(i in 1..amt) mVendingMachine.insertCoin(coin)
    }
}