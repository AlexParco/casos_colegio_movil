package com.casos.colegio.app.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Mensaje(
    @SerializedName("id") var id: String?,
    @SerializedName("body") var body: String?,
    @SerializedName("usuario") var user: User?,
    @SerializedName("estado") var estado: Boolean?,
): Serializable
