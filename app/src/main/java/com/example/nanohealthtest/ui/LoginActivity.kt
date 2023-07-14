package com.example.nanohealthtest.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nanohealthtest.databinding.ActivityLoginBinding
import com.example.nanohealthtest.model.data.LoginData
import com.example.nanohealthtest.ui.viewmodel.LoginViewModel

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        val loginViewModel by viewModels<LoginViewModel>()

        setLoginStateObserver(binding, loginViewModel)
        setUiStateObserver(binding, loginViewModel)
        setLoginButtonBehavior(binding, loginViewModel)
        setShowPasswordButtonBehavior(binding, loginViewModel)

        setContentView(binding.root)
    }

    private fun setLoginStateObserver(binding: ActivityLoginBinding, loginViewModel: LoginViewModel) {
        loginViewModel.loginState.observe(this) { newLoginState ->
            if (newLoginState.successfulLogin) {
                if (!newLoginState.initial) {
                    binding.loginSuccessfulIcon.visibility = View.VISIBLE
                    binding.loginFailedIcon.visibility = View.INVISIBLE
                }
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                startActivity(mainActivityIntent)
            } else {
                binding.passwordInput.setText("")
                if (!newLoginState.initial) {
                    binding.loginSuccessfulIcon.visibility = View.INVISIBLE
                    binding.loginFailedIcon.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUiStateObserver(binding: ActivityLoginBinding, loginViewModel: LoginViewModel) {
        loginViewModel.uiState.observe(this) { newUiState ->
            if (newUiState.passwordVisible) {
                binding.passwordInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.passwordShowIcon.imageAlpha = 200
            } else {
                binding.passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordShowIcon.imageAlpha = 50
            }
        }
    }

    private fun setLoginButtonBehavior(binding: ActivityLoginBinding, loginViewModel: LoginViewModel) {
        binding.continueButton.setOnClickListener {
            val username = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val loginData = LoginData(username, password)
            loginViewModel.login(loginData)
        }
    }

    private fun setShowPasswordButtonBehavior(binding: ActivityLoginBinding, loginViewModel: LoginViewModel) {
        binding.passwordShowIcon.setOnClickListener {
            loginViewModel.changePasswordVisibility()
        }
    }
}