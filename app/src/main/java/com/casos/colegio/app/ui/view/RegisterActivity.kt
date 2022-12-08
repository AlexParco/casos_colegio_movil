package com.casos.colegio.app.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.casos.colegio.app.data.models.User
import com.casos.colegio.app.databinding.ActivityRegisterBinding
import com.casos.colegio.app.ui.view.BaseActivity
import com.casos.colegio.app.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = RegisterViewModel()

        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener {
            registerViewModel.registerUser(
                User(
                    "0",
                    binding.edtNombres.text.toString(),
                    binding.edtApellidos.text.toString(),
                    binding.edtEdad.text.toString(),
                    "alumno",
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
            ))
        }

        registerViewModel.emptyFieldError.observe(this) {
            Toast.makeText(this, "Ingrese los datos de usuario", Toast.LENGTH_SHORT).show();
        }

        registerViewModel.fieldsAuthenticationError.observe(this) {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

        registerViewModel.goSuccessActivity.observe(this) {
            Toast.makeText(this, "Porfavor Inicie Sesion", Toast.LENGTH_SHORT).show();
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}