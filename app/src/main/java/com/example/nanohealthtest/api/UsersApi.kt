package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.domain.LoginInfo

object UsersApi {
    suspend fun doesUserExist(loginInfo: LoginInfo): Boolean {
        return loginInfo.email == "mor_2314" && loginInfo.password == "83r5^_"
    }
}