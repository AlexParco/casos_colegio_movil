package com.casos.colegio.app.data.service

import com.casos.colegio.app.data.models.Comment
import com.casos.colegio.app.data.models.CommentDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CommentService {

    @Headers("Content-Type: application/json")
    @GET("comment")
    suspend fun findAllComments(@Query("mensajeid") mensajeid: String): Response<List<Comment>>

    @Headers("Content-Type: application/json")
    @POST("comment/{id}")
    suspend fun createComment(@Path("id") id: String ,@Body comment: CommentDTO): Response<Comment>

    companion object {
        fun build(): CommentService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.34:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CommentService::class.java)
        }
    }
}

