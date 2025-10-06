package com.pampang.nav.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pampang.nav.R
import com.pampang.nav.databinding.ActivityBuyerMainBinding
import com.pampang.nav.screens.auth.LoginActivity
import com.pampang.nav.utilities.extension.launchActivity
import com.pampang.nav.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class BuyerMainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityBuyerMainBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConfig()
    }

    private fun initConfig() {
        initBinding()
        initEventListener()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_buyer_main)
        mBinding.lifecycleOwner = this
    }

    private fun initEventListener() {
        mBinding.apply {
            textViewProfile.setOnClickListener {
                launchActivity<ProfileActivity>()
            }
        }
    }
}