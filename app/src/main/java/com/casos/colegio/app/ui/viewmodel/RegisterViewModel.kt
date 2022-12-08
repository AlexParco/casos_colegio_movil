package com.casos.colegio.app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel (
): ViewModel() {

    val emptyFieldError = MutableLiveData<Boolean>()
    val fieldsAuthenticationError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    private val userService by lazy {
        UserService.build()
    }

    fun registerUser(user: User) {
        if(user.email!!.isEmpty() || user.password!!.isEmpty() || user.nombre!!.isEmpty()) {
            emptyFieldError.postValue(true)
        }

        CoroutineScope(Dispatchers.IO).launch{
            val call = userService.registerService(user)
            if(call.isSuccessful) {
                call.body()?.let {
                    goSuccessActivity.postValue(true)
                }
            }else {
                fieldsAuthenticationError.postValue(true)
            }
        }
    }
}