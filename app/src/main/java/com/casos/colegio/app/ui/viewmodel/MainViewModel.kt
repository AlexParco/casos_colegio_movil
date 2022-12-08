package com.casos.colegio.app.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.UserService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val alumnosList = MutableLiveData<List<User>>()

    init {
        getAllAlumnos()
    }

    fun getAllAlumnos() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = UserService.build().findAllUsers()
            if(call.isSuccessful){
                call.body()?.let {
                    alumnosList.postValue(it)
                    Log.i("ALUMNOS", it.get(1).toString())
                }
            }else {
                Log.i("TEST","ERROR en alumnos")
            }
        }
    }
}