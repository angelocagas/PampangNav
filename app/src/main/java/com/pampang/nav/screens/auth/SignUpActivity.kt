package com.pampang.nav.screens.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.angelodev.ggbonuscalc.utilities.extension.showToast
import com.pampang.nav.R
import com.pampang.nav.databinding.ActivitySignupBinding
import com.pampang.nav.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignupBinding
    private val mAuthViewModel: AuthViewModel by viewModels()

    private var selectedRole: String? = null  // Store selected role (Buyer or Seller)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConfig()
    }

    private fun initConfig() {
        initBinding()
        initEventListener()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mBinding.lifecycleOwner = this
    }

    private fun initEventListener() {
        mBinding.apply {
            textViewLogin.setOnClickListener {
                finish()
            }

            // --- ROLE SELECTION ---
            buttonBuyer.setOnClickListener {
                selectRole("buyer")
            }

            buttonSeller.setOnClickListener {
                selectRole("seller")
            }

            // --- REGISTER BUTTON ---
            buttonRegister.setOnClickListener {
                val email = edittextEmail.text.toString().trim()
                val password = edittextPassword.text.toString().trim()
                val username = edittextUsername.text.toString().trim()
                val role = selectedRole

                if (role.isNullOrEmpty()) {
                    showToast("Please select a role")
                    return@setOnClickListener
                }

                mAuthViewModel.register(email, password, username, role)
            }
        }
    }

    // --- ROLE SELECTION HANDLER ---
    private fun selectRole(role: String) {
        selectedRole = role

        val colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary)
        val white = ContextCompat.getColor(this, android.R.color.white)

        mBinding.apply {
            if (role == "buyer") {
                buttonBuyer.setBackgroundColor(colorPrimary)
                buttonBuyer.setTextColor(white)
                buttonSeller.setBackgroundColor(white)
                buttonSeller.setTextColor(colorPrimary)
            } else {
                buttonSeller.setBackgroundColor(colorPrimary)
                buttonSeller.setTextColor(white)
                buttonBuyer.setBackgroundColor(white)
                buttonBuyer.setTextColor(colorPrimary)
            }
        }
    }

}
