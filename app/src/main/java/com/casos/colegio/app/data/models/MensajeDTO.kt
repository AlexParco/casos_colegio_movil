package com.casos.colegio.app.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MensajeDTO(
    @SerializedName("id") var id: String?,
    @SerializedName("estado") var estado: Boolean?,
    @SerializedName("user_id") var user: String?,
    @SerializedName("body") var body: String?
): Serializable
