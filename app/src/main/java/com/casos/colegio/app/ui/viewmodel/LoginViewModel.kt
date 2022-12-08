package com.casos.colegio.app.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casos.colegio.app.data.models.LoginRequest
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.UserService
import com.casos.colegio.app.util.SharedPreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (
    private var context: Context
): ViewModel() {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    var user: User? = null

    val emptyFieldError = MutableLiveData<Boolean>()
    val fieldsAuthenticationError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    private val userService by lazy {
        UserService.build()
    }

    fun onCreate() {
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun loginUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()){
            emptyFieldError.postValue(true)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val call = userService.loginService(LoginRequest(email = email, password = password))
            if(call.isSuccessful) {
                call.body()?.let {
                    sharedPreferenceUtil.saveUser(it)
                    user = it
                    goSuccessActivity.postValue(true)
                }
            }else {
                fieldsAuthenticationError.postValue(false)
            }
        }
    }
}
