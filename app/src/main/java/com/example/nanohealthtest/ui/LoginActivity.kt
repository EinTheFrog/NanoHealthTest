package com.example.nanohealthtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.nanohealthtest.databinding.ActivityLoginBinding
import com.example.nanohealthtest.model.domain.LoginInfo
import com.example.nanohealthtest.ui.viewmodel.LoginViewModel

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginViewModel by viewModels<LoginViewModel>()
        loginViewModel.uiState.observe(this) { newUiState ->
            if (newUiState.successfulLogin) {
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                startActivity(mainActivityIntent)
            }
        }

        binding.continueButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val loginInfo = LoginInfo(email, password)
            loginViewModel.login(loginInfo)
        }
    }
}