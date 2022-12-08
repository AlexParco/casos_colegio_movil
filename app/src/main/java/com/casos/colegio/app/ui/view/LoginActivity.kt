package com.casos.colegio.app.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.casos.colegio.app.databinding.ActivityLoginBinding
import com.casos.colegio.app.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = LoginViewModel(this)

        loginViewModel.onCreate()

        binding.btnLogin.setOnClickListener{
            loginViewModel.loginUser(
                binding.edtEmail.text.toString(),
                binding.edtPassword.text.toString(),
            )
    }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginViewModel.emptyFieldError.observe(this) {
            Toast.makeText(this, "Ingrese los datos de usuario", Toast.LENGTH_SHORT).show();
        }
        loginViewModel.fieldsAuthenticationError.observe(this) {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

        loginViewModel.goSuccessActivity.observe(this) {
            if(loginViewModel.user?.rol == "profesor") {
                val intent = Intent(this,  MainActivity::class.java)
                intent.putExtra("USER_LOGIN", loginViewModel.user)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this,  AlumnoActivity::class.java)
                intent.putExtra("USER", loginViewModel.user)
                startActivity(intent)
                finish()
            }
        }
    }


}