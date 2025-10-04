package com.pampang.nav.screens.auth

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pampang.nav.R
import com.pampang.nav.databinding.ActivityLoginBinding
import com.pampang.nav.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySignupBinding
//    private val mAuthViewModel: AuthenticationViewModel by viewModels()
    private var mPermissionGranted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initConfig()
    }

    private fun initConfig() {
        initBinding()
        initEventListener()
        initLiveData()
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        mBinding.lifecycleOwner = this
//        mBinding.viewModel = mAuthViewModel
//        hideKeyboardOnOutsideTouch(this, mBinding.constraintLayoutContent)

    }

    private fun initEventListener() {
//        mBinding.apply {
//            buttonLogin.setSafeOnClickListener {
//                if (mPermissionGranted) {
//                    val email = edittextUsername.text?.toString()?.trim()
//                    val password = edittextPassword.text?.toString()?.trim()
//                    val isRememberMe = checkboxRememberMe.isChecked
//
//                    mAuthViewModel.onLogin(email, password, isRememberMe)
//                } else {
//                    initLocationChecker(this@LoginActivity, this@LoginActivity, true)
//                }
//            }
//
//            textViewSignUpNow.setSafeOnClickListener {
//                launchActivity<RegisterBasicInfoActivity> {
//
//                }
//            }
//
//            textForgot.setSafeOnClickListener {
//                launchActivity<ForgotPasswordActivity> { }
//            }
//
//            mBinding.edittextUsername.addTextChangedListener { text ->
//                if (!text.isNullOrEmpty()) setErrorEnabled(
//                    mBinding.textInputLayoutEmail
//                )
//            }
//            mBinding.edittextPassword.addTextChangedListener { text ->
//                if (!text.isNullOrEmpty()) setErrorEnabled(
//                    mBinding.textInputLayoutPassword
//                )
//            }
//        }
        mBinding.apply {
            textViewLogin.setOnClickListener {
                finish()
            }
        }
    }

    private fun initLiveData() {

    }

}