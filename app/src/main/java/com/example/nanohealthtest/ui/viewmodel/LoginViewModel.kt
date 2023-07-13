package com.example.nanohealthtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanohealthtest.model.domain.LoginInfo
import com.example.nanohealthtest.usecases.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    data class UiState(
        val successfulLogin: Boolean
    )

    private val _uiState = MutableLiveData(UiState(false))
    val uiState: LiveData<UiState> = _uiState

    fun login(loginInfo: LoginInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            val successfulLogin = UserUseCase.login(loginInfo)
            _uiState.postValue(_uiState.value?.copy(successfulLogin = successfulLogin))
        }
    }
}