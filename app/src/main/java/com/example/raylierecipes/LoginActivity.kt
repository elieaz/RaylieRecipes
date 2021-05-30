package com.example.raylierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.btnLogin.isVisible = true
        binding.btnLogRegister.isVisible = true


        binding.txtForgotPass.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.btnLogRegister.setOnClickListener(this)
            }


    override fun onClick(v: View?) {

        if(v!=null) {
            when(v.id) {
                R.id.txtForgotPass ->{
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btnLogin ->{
                    loginUser()
                }
                R.id.btnLogRegister ->{
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean{
        return when {

            //checking that all required fields are filled before registration

            binding.inputEmail.text.toString().trim{it <= ' '}.isEmpty() -> {
                binding.inputEmail.error = "Required"
                Toast.makeText(applicationContext, "Email Required", Toast.LENGTH_SHORT).show()
                false
            }
            binding.inputPassword.text.toString().trim{it <= ' '}.isEmpty() -> {
                binding.inputPassword.error = "Required"
                Toast.makeText(applicationContext, "Password Required", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                binding.btnLogin.isVisible = false
                binding.btnLogRegister.isVisible = false
                true

            }}}

    private fun loginUser(){

        if(validateLoginDetails()){
            val email: String = binding.inputEmail.text.toString().trim{it <= ' '}
            val password: String = binding.inputPassword.text.toString().trim{it <= ' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{task ->
                if(task.isSuccessful){

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                    finish()

                } else{
                    Toast.makeText(applicationContext, "Error\n ${task.exception!!.message.toString()}", Toast.LENGTH_LONG).show()
                    binding.btnLogin.isVisible = true
                    binding.btnLogRegister.isVisible = true
                }
            }
        }
    }

}