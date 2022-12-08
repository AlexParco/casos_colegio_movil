package com.casos.colegio.app.data.service

import com.casos.colegio.app.data.models.LoginRequest
import com.casos.colegio.app.data.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @Headers("Content-Type: application/json")
    @POST("usuario/login")
    suspend fun loginService(@Body user: LoginRequest): Response<User>

    @Headers("Content-Type: application/json")
    @POST("usuario/register")
    suspend fun registerService(@Body user: User): Response<User>

    @GET("usuario")
    suspend fun findAllUsers(): Response<List<User>>

    companion object {
        fun build(): UserService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.34:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserService::class.java)
        }
    }

}