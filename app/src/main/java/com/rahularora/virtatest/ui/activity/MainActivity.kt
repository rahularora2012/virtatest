package com.rahularora.virtatest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rahularora.virtatest.api.response.BaseResponse
import com.rahularora.virtatest.api.response.LoginResponse
import com.rahularora.virtatest.databinding.ActivityMainBinding
import com.rahularora.virtatest.utils.AppUtils
import com.rahularora.virtatest.utils.AppUtils.showToast
import com.rahularora.virtatest.utils.SessionManager
import com.rahularora.virtatest.viewmodel.LoginViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        observeLoginResult()

        binding.buttonLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun observeLoginResult() {
        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    stopLoading()
                    processError(it.errorResponse)
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun doLogin() {
        val email = binding.eTEmailAddress.text.toString()
        val password = binding.eTPassword.text.toString()

        if (AppUtils.isEmailValid(email) && password.isNotEmpty()) {
            viewModel.loginUser(email = email, pwd = password)
        } else {
            showToast(this,"Invalid email or empty password field")
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun processLogin(data: LoginResponse?) {
        showToast(this,"Success")
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let {
                SessionManager.saveAuthToken(this, it)
            }
            navigateToHome()
        }
    }

    private fun processError(msg: String?) {
        if(!msg.isNullOrEmpty()) {
            showToast(this,msg)
        }
    }

}
