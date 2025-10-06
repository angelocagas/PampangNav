package com.pampang.nav.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pampang.nav.R
import com.pampang.nav.databinding.ActivityBuyerMainBinding
import com.pampang.nav.databinding.ActivitySellerMainBinding
import com.pampang.nav.screens.auth.LoginActivity
import com.pampang.nav.utilities.extension.launchActivity
import com.pampang.nav.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerMainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySellerMainBinding
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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_seller_main)
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