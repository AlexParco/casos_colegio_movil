package com.casos.colegio.app.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.casos.colegio.app.data.models.Mensaje
import com.casos.colegio.app.data.models.MensajeDTO
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.data.service.MensajeService
import com.casos.colegio.app.databinding.ActivityAlumnoBinding
import com.casos.colegio.app.ui.adapter.MensajeAdapter
import com.casos.colegio.app.ui.viewmodel.AlumnoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoActivity : BaseActivity<ActivityAlumnoBinding>(ActivityAlumnoBinding::inflate), MensajeAdapter.Callback  {

    private var mensajeList : List<Mensaje> = emptyList()

    private val alumnoViewModel by lazy { AlumnoViewModel(user.id.toString(), this) }

    private val adapter by lazy { MensajeAdapter(mensajeList, this, alumnoViewModel.userLogin)}

    private val user by lazy {
        intent.getSerializableExtra("USER") as User
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(alumnoViewModel.userLogin?.rol == "profesor") {
            binding.btnSend.visibility = View.GONE
            binding.mensajeInput.visibility = View.GONE
        }

        binding.btnSend.setOnClickListener {
            val mensajeDTO = MensajeDTO(
                "0",
                false,
                alumnoViewModel.userLogin?.id.toString(),
                binding.mensajeInput.text.toString())
            alumnoViewModel.sendMensaje(mensajeDTO)
            binding.mensajeInput.text.clear()
        }

        binding.mensajesList.layoutManager = LinearLayoutManager(this)
        binding.mensajesList.setHasFixedSize(true)
        binding.mensajesList.adapter = adapter


        alumnoViewModel.mensajesList.observe(this){
            adapter.mensajes = it
            adapter.notifyDataSetChanged()
        }
    }

    override fun getMensajeInformation(mensaje: Mensaje) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("MENSAJE", mensaje)
        startActivity(intent)
    }

    override fun deleteMensaje(mensajeId: String) {
        alumnoViewModel.deleteMensaje(mensajeId)
    }
}