package com.casos.colegio.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.databinding.ItemAlumnoBinding

class AlumnoAdapter(
    var list: List<User>,
    val callback: Callback
):
    RecyclerView.Adapter<AlumnoAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemAlumnoBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(alumno: User) {
            binding.edtNombres.text = alumno.nombre
            binding.edtEmail.text = alumno.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemAlumnoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val alumno: User =list[position]
        holder.itemView.setOnClickListener{
            callback.getInformationAlumno(alumno)
        }
        holder.bind(alumno)
    }

    override fun getItemCount(): Int = list.size


    interface Callback {
        fun getInformationAlumno(alumno: User)
    }
}


