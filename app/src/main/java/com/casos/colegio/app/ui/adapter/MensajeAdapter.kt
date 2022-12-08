package com.casos.colegio.app.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casos.colegio.app.data.models.Mensaje
import com.casos.colegio.app.data.models.MensajeDTO
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.MensajeService
import com.casos.colegio.app.data.service.UserService
import com.casos.colegio.app.databinding.ItemMensajeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MensajeAdapter(
    var mensajes: List<Mensaje>,
    val callback: Callback,
    val user: User?
    ):
    RecyclerView.Adapter<MensajeAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMensajeBinding, private val user: User?, val callback: Callback):

        RecyclerView.ViewHolder(binding.root){

        fun bind(mensaje: Mensaje) {
            binding.state.text = if(mensaje.estado == true) "revisado" else  "no-revisado"
            if(user?.rol == "alumno") {
                binding.btnSucces.visibility = View.GONE
                binding.btnDelete.visibility = View.GONE
            }

            binding.btnDelete.setOnClickListener{
                callback.deleteMensaje(mensaje.id.toString())
            }

            binding.btnSucces.setOnClickListener{
                mensaje.estado = !mensaje.estado!!

                binding.state.text = if(mensaje.estado == true) "revisado" else  "no-revisado"

                CoroutineScope(Dispatchers.IO).launch {
                    MensajeService.build().editMensaje(mensaje.id.toString(),MensajeDTO(
                        mensaje.id.toString(),
                        mensaje.estado,
                        mensaje.user?.id,
                    mensaje.body,
                    ))
                }
            }

            binding.msgUser.text = mensaje.user?.nombre
            binding.msgBody.text = mensaje.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMensajeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding, user, callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mensaje: Mensaje = mensajes[position]
        holder.itemView.setOnClickListener{
            callback.getMensajeInformation(mensaje)
        }
        holder.bind(mensajes[position])
    }

    interface  Callback {
        fun getMensajeInformation(mensaje: Mensaje)
        fun deleteMensaje(mensajeId: String)
    }

    override fun getItemCount(): Int = mensajes.size

}