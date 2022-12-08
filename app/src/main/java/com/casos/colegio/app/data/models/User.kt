package com.casos.colegio.app.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") var id: String?,
    @SerializedName("nombre") var nombre: String?,
    @SerializedName("apellido") var apellido: String?,
    @SerializedName("edad") var edad: String?,
    @SerializedName("rol") var rol: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("password") var password: String?,
): Serializable {
}