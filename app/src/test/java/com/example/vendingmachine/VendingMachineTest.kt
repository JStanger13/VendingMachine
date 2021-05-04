package com.example.vendingmachine

import com.example.vendingmachine.DisplayConstants.CANDY
import com.example.vendingmachine.DisplayConstants.CHIPS
import com.example.vendingmachine.DisplayConstants.COLA
import com.example.vendingmachine.DisplayConstants.EXACT_CHANGE_ONLY
import com.example.vendingmachine.DisplayConstants.INSERT_COIN
import com.example.vendingmachine.DisplayConstants.NICKEL
import com.example.vendingmachine.DisplayConstants.QUARTER
import com.example.vendingmachine.DisplayConstants.SOLD_OUT
import com.example.vendingmachine.DisplayConstants.THANK_YOU
import com.example.vendingmachine.model.product.Candy
import com.example.vendingmachine.model.product.Chips
import com.example.vendingmachine.model.product.Cola
import com.example.vendingmachine.model.product.Product
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(VendingMachine::class)
class VendingMachineTest {

    private lateinit var mCola: Cola
    private lateinit var mChips: Chips
    private lateinit var mCandy: Candy

    private lateinit var mVendingMachine: VendingMachine

    @Before
    fun setup() {
        mCola = Cola()
        mChips = Chips()
        mCandy = Candy()
        mVendingMachine = VendingMachine()
    }


    @Test
    fun testPressButton_DisplaysSoldOut() {
        //Cola
        buyOutProduct(mCola, QUARTER, 4)
        insertEnoughCoinsForProduct(QUARTER, 4)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "$SOLD_OUT: $COLA")

        //Chips
        buyOutProduct(mChips, QUARTER, 2)
        insertEnoughCoinsForProduct(QUARTER, 2)
        mVendingMachine.pressButton(mChips)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "$SOLD_OUT: $CHIPS")

        //Candy
        buyOutProduct(this.mCandy, NICKEL, 13)
        insertEnoughCoinsForProduct(NICKEL, 13)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "$SOLD_OUT: $CANDY")

    }

    @Test
    fun testPressButton_DisplaysExactChangeOnly() {
        insertEnoughCoinsForProduct(QUARTER, 3)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == EXACT_CHANGE_ONLY)
    }

    @Test
    fun testPressButton_DisplaysThankYou() {
        insertEnoughCoinsForProduct(QUARTER, 4)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == THANK_YOU)
    }

    @Test
    fun testPressButton_DisplaysPrice() {
        //Cola
        insertEnoughCoinsForProduct(QUARTER, 1)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "PRICE $1.00")

        //Chips
        mVendingMachine.pressButton(mChips)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "PRICE $0.50")

        //Candy
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == "PRICE $0.65")
    }

    @Test
    fun testPressButton_CanMakeChange() {
        insertEnoughCoinsForProduct(QUARTER, 5)
        mVendingMachine.pressButton(mCola)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == THANK_YOU)
    }

    @Test
    fun testReturnCoins_displaysZeroCents() {
        assert(mVendingMachine.getCurrentAmt() == " $0.00")
        mVendingMachine.insertCoin(QUARTER)
        assert(mVendingMachine.getCurrentAmt() == " $0.25")
        mVendingMachine.returnCoins()
        assert(mVendingMachine.getCurrentAmt() == " $0.00")
    }

    @Test
    fun testExactChangeOnlyFunctionality() {
        assert(mVendingMachine.getDisplay().getDisplayMessage() == EXACT_CHANGE_ONLY)

        //Cannot dispense change for .65 with only quarters
        insertEnoughCoinsForProduct(QUARTER, 3)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == EXACT_CHANGE_ONLY)

        //User gets extra quarter returned
        mVendingMachine.pressButton(mChips)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == THANK_YOU)

        //Still cannot dispense change for .65 with only quarters
        mVendingMachine.insertCoin(QUARTER)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == EXACT_CHANGE_ONLY)

        insertEnoughCoinsForProduct(NICKEL, 13)
        mVendingMachine.pressButton(mCandy)

        //Now there's enough change for .65 with only quarters
        insertEnoughCoinsForProduct(NICKEL, 13)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == INSERT_COIN)
        mVendingMachine.pressButton(mCandy)
        assert(mVendingMachine.getDisplay().getDisplayMessage() == THANK_YOU)
    }

    private fun buyOutProduct(product: Product, coin: Int, amt: Int) {
        for (i in 1..10) {
            insertEnoughCoinsForProduct(coin, amt)
            mVendingMachine.pressButton(product)
        }
    }

    private fun insertEnoughCoinsForProduct(coin: Int, amt: Int) {
        for(i in 1..amt) mVendingMachine.insertCoin(coin)
    }
}