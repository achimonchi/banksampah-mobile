package com.example.banksampah.api.implementation

import com.example.banksampah.api.ApiService
import com.example.banksampah.api.helper.AuthHelper
import com.example.banksampah.model.entity.AuthItem
import javax.inject.Inject

class AuthImpl @Inject constructor(
    private val apiService: ApiService
) : AuthHelper {

    override suspend fun authLogin(authItem: AuthItem.Data) = apiService.authLogin(authItem)

    override suspend fun authSignup(authItem: AuthItem.Data) = apiService.authSignup(authItem)

}