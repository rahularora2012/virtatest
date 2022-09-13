package com.rahularora.virtatest.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.io.IOException

object AppUtils {
    const val BASE_URL = "https://apitest.virta.fi/v4/"

    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}