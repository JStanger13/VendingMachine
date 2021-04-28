package com.example.vendingmachine.model

import com.example.vendingmachine.Display
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VendingMachineTest {

    private lateinit var mCola: Cola
    private lateinit var mChips: Chips
    private lateinit var mCandy: Candy
    lateinit var mVendingMachine: VendingMachine

    @Before
    fun setup() {
        mVendingMachine = VendingMachine()
        mCola = Cola()
        mChips = Chips()
        mCandy = Candy()
    }

    //Coin Tests
    @Test
    fun testConvertCentsToDollars() {
        val str = mVendingMachine.convertCentsToDollars(20)
        assert(str == "0.20")
    }

    @Test
    fun returnChange() {
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        assert(mVendingMachine.canMakeChange(mChips))
    }

    @Test
    fun cannotReturnChange() {
        mVendingMachine.insertCoin(75)
        assert(!mVendingMachine.canMakeChange(mChips))
    }

    //Cola tests
    @Test
    fun testPressButtonColaSuccess() {
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)

        val str = mVendingMachine.pressButton(mCola)
        Assert.assertEquals(Display.THANK_YOU, str)
    }

    @Test
    fun testPressButtonColaNotEnoughMoneyInserted() {
        val str = mVendingMachine.pressButton(mCola)
        val ans = "PRICE $1.00"
        Assert.assertEquals(str, ans)
    }

    @Test
    fun testPressButtonColaSoldOut() {
        buyOutProducts(mCola)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)

        val str = mVendingMachine.pressButton(mCola)
        assert(str == Display.SOLD_OUT)
    }

    //Chips tests
    @Test
    fun testPressButtonChipsSuccess() {
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        val str = mVendingMachine.pressButton(mChips)
        Assert.assertEquals(Display.THANK_YOU, str)
    }

    @Test
    fun testPressButtonChipsNotEnoughMoneyInserted() {
        val str = mVendingMachine.pressButton(mChips)
        val ans = "PRICE $0.50"
        Assert.assertEquals(str, ans)
    }
    @Test
    fun testPressButtonChipsSoldOut() {
        buyOutProducts(mChips)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        val str = mVendingMachine.pressButton(mChips)
        assert(str == "SOLD OUT")
    }

    //Candy tests
    @Test
    fun testPressButtonCandySuccess() {
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(25)
        mVendingMachine.insertCoin(10)
        mVendingMachine.insertCoin(5)

        val str = mVendingMachine.pressButton(mCandy)
        Assert.assertEquals(Display.THANK_YOU, str)
    }
    @Test
    fun testPressButtonCandyNotEnoughMoneyInserted() {
        val str = mVendingMachine.pressButton(mCandy)
        val ans = "PRICE $0.65"
        Assert.assertEquals(str, ans)
    }
    @Test
    fun testPressButtonCandySoldOut() {
        buyOutProducts(mCandy)
        mVendingMachine.insertCoin(65)
        val str = mVendingMachine.pressButton(mCandy)
        assert(str == "SOLD OUT")
    }

    private fun buyOutProducts(product: Product) {
        for (i in 1..10) {
            mVendingMachine.insertCoin(25)
            mVendingMachine.insertCoin(25)
            mVendingMachine.insertCoin(25)
            mVendingMachine.insertCoin(25)
            mVendingMachine.pressButton(product)
        }
    }
}