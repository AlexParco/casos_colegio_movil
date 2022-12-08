package com.casos.colegio.app.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.casos.colegio.app.data.models.Comment
import com.casos.colegio.app.data.models.CommentDTO
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.CommentService
import com.casos.colegio.app.data.service.MensajeService
import com.casos.colegio.app.util.SharedPreferenceUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(
    val mensajeId: String,
    private val context: Context
): ViewModel() {
    val commentList = MutableLiveData<List<Comment>>()
    var userLogin: User? = null

    init {
        getAllComments(mensajeId)
        userLogin = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }.getUser()
    }

    private fun getAllComments(mensajeId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = CommentService.build().findAllComments(mensajeId)
            if(call.isSuccessful) {
                call.body()?.let{
                    commentList.postValue(it)
                }
            }
        }
    }

    fun saveComment(comment: CommentDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = CommentService.build().createComment(mensajeId,comment)
            if(call.isSuccessful) {
                getAllComments(mensajeId)
            }
        }
    }
}