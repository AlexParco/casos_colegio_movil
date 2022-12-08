package com.casos.colegio.app.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.databinding.ActivityMainBinding
import com.casos.colegio.app.ui.adapter.AlumnoAdapter
import com.casos.colegio.app.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), AlumnoAdapter.Callback {

    private var alumnosList: List<User> = emptyList()

    private val adapter by lazy { AlumnoAdapter(alumnosList, this)}

    private val mainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.alumnosList.layoutManager = LinearLayoutManager(this)
        binding.alumnosList.setHasFixedSize(true)
        binding.alumnosList.adapter = adapter

        mainViewModel.alumnosList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }
    }

    override fun getInformationAlumno(alumno: User) {
        val intent = Intent(this, AlumnoActivity::class.java)
        intent.putExtra("USER", alumno)
        startActivity(intent)
    }

}