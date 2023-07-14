package com.example.nanohealthtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanohealthtest.model.data.LoginData
import com.example.nanohealthtest.usecases.ShopUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    data class UiState(
        val passwordVisible: Boolean
    )

    data class LoginState(
        val initial: Boolean,
        val successfulLogin: Boolean
    )

    private val _uiState = MutableLiveData(UiState(false))
    val uiState: LiveData<UiState> = _uiState

    private val _loginState = MutableLiveData(LoginState(initial = true, successfulLogin = false))
    val loginState: LiveData<LoginState> = _loginState

    fun login(loginData: LoginData) {
        viewModelScope.launch(Dispatchers.IO) {
            val successfulLogin = ShopUseCase.login(loginData)
            _loginState.postValue(_loginState.value?.copy(initial = false, successfulLogin = successfulLogin))
        }
    }

    fun changePasswordVisibility() {
        val oldVisibility = _uiState.value?.passwordVisible ?: return
        _uiState.postValue(_uiState.value?.copy(passwordVisible = !oldVisibility))
    }
}