package com.example.raylierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_forgot_password)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password)
        binding.floatingActionButton3.setOnClickListener {
            finish()
        }

        binding.btnSubmit.isVisible = true
        binding.floatingActionButton3.isVisible = true

        binding.btnSubmit.setOnClickListener{
            val email: String = binding.inputForgotPass.text.toString().trim{it <= ' '}
            if (email.isEmpty()) {
                binding.inputForgotPass.error = "Required"
                Toast.makeText(applicationContext, "Please Enter Email Address", Toast.LENGTH_SHORT).show()
            }else{
                binding.btnSubmit.isVisible = false
                binding.floatingActionButton3.isVisible = false

                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Email Sent to Reset Your Password", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext, "Error\n ${task.exception!!.message.toString()}", Toast.LENGTH_LONG).show()
                        binding.btnSubmit.isVisible = true
                        binding.floatingActionButton3.isVisible = true

                    }
                }
            }
        }
    }
}