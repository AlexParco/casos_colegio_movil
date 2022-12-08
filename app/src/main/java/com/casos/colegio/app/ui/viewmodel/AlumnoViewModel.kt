package com.casos.colegio.app.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casos.colegio.app.data.models.Mensaje
import com.casos.colegio.app.data.models.MensajeDTO
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.MensajeService
import com.casos.colegio.app.util.SharedPreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoViewModel(
    val userId: String,
    private val context: Context
): ViewModel() {
    val mensajesList = MutableLiveData<List<Mensaje>>()
    var userLogin: User? = null

    init {
        getAllMensaje(userId)
        userLogin = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }.getUser()
    }

    fun getAllMensaje(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MensajeService.build().findAllMensajes(id)
            if(call.isSuccessful) {
                call.body()?.let{
                    mensajesList.postValue(it)
                }
            }
        }
    }

    fun sendMensaje(mensaje: MensajeDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = MensajeService.build().saveMensaje(mensaje)
            if(call.isSuccessful) {
                getAllMensaje(userId)
            }
        }
    }

    fun deleteMensaje(mensajeId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            MensajeService.build().deleteMensaje(mensajeId)
            getAllMensaje(userId)
        }
    }
}