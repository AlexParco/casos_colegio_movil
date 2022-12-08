package com.casos.colegio.app.data.service

import com.casos.colegio.app.data.models.Mensaje
import com.casos.colegio.app.data.models.MensajeDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Headers
import retrofit2.http.Path

interface MensajeService {

    @Headers("Content-Type: application/json")
    @GET("mensaje/{id}")
    suspend fun findAllMensajes(@Path("id") id: String): Response<List<Mensaje>>

    @Headers("Content-Type: application/json")
    @PUT("mensaje/{id}")
    suspend fun editMensaje(@Path("id") id: String, @Body mensaje: MensajeDTO)

    @Headers("Content-Type: application/json")
    @DELETE("mensaje/{id}")
    suspend fun deleteMensaje(@Path("id") id: String)

    @Headers("Content-Type: application/json")
    @POST("mensaje")
    suspend fun saveMensaje(@Body mensaje: MensajeDTO): Response<Mensaje>

    companion object {
        fun build(): MensajeService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.34:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MensajeService::class.java)
        }
    }

}