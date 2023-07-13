package com.example.nanohealthtest.usecases

import com.example.nanohealthtest.api.UsersApi
import com.example.nanohealthtest.model.domain.LoginInfo

object UserUseCase {
    suspend fun login(loginInfo: LoginInfo): Boolean {
        return UsersApi.doesUserExist(loginInfo)
    }
}