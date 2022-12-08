package com.casos.colegio.app.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CommentDTO(
    @SerializedName("body") var body: String?,
    @SerializedName("user_id") var user: String?,
    @SerializedName("mensaje_id") var mensajeId: String?
): Serializable
