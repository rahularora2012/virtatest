package com.rahularora.virtatest.repository

import com.rahularora.virtatest.api.methods.UserLoginApi
import com.rahularora.virtatest.api.models.Login
import com.rahularora.virtatest.api.response.LoginResponse
import retrofit2.Response

class LoginRepository {

   suspend fun loginUser(loginRequest: Login): Response<LoginResponse>? {
      return  UserLoginApi.getApi()?.login(loginRequest = loginRequest)
    }
}