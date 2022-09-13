package com.rahularora.virtatest.api.methods

import com.rahularora.virtatest.api.ApiClient
import com.rahularora.virtatest.api.models.Login
import com.rahularora.virtatest.api.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserLoginApi {

    @POST("auth")
    suspend fun login(@Body loginRequest: Login): Response<LoginResponse>

    companion object {
        fun getApi(): UserLoginApi? {
            return ApiClient.client?.create(UserLoginApi::class.java)
        }
    }
}