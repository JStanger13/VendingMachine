package com.example.vendingmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.vendingmachine.databinding.ActivityMainBinding
import com.example.vendingmachine.ui.VendingMachineViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: VendingMachineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(VendingMachineViewModel::class.java)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mViewModel
    }
}