package com.rahularora.virtatest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.rahularora.virtatest.api.models.Login
import com.rahularora.virtatest.api.response.BaseResponse
import com.rahularora.virtatest.api.response.LoginResponse
import com.rahularora.virtatest.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepo = LoginRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val login = Login(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = login)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 401){
                    loginResult.value = BaseResponse.Error("Invalid email or password")
                } else {
                    loginResult.value = BaseResponse.Error("Something went wrong! Please ty again.")
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}