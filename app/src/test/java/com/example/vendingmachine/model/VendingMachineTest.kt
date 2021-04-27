package com.example.vendingmachine.model

import com.example.vendingmachine.model.product.Product
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VendingMachineTest {

    @Mock
    private lateinit var mProduct: Product

    lateinit var mVendingMachine: VendingMachine

    @Before
    fun setup() {
        mVendingMachine = VendingMachine()
    }

    @Test
    fun testSelectProduct() {
        mVendingMachine.selectProduct(mProduct)
    }

    @Test
    fun testSelectCola() {
        mVendingMachine.selectProduct(mProduct)
    }

    @Test
    fun testDispense(){
        mVendingMachine.dispense()
    }

    @Test
    fun testInsertCoin() {
        mVendingMachine.insertCoin(Mockito.anyInt())
    }

    @Test
    fun testReturnCoins() {
        mVendingMachine.returnCoins()
    }
}