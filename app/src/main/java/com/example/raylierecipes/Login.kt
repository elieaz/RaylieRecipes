package com.example.raylierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.btnLogRegister.setOnClickListener{
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish() //added finish to not layer activities on top of each other


            binding.btnLogin.setOnClickListener{ //checking that all required fields are filled before registration

                if(binding.inputEmail.text.toString().trim().isEmpty()){
                    binding.inputEmail.error = "Required"
                    Toast.makeText(applicationContext, "Email ID Required", Toast.LENGTH_SHORT).show()
                }
                else if (binding.inputPassword.text.toString().trim().isEmpty()) {
                    binding.inputPassword.error = "Required"
                    Toast.makeText(applicationContext, "Password Required", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, "Login Successful ", Toast.LENGTH_SHORT).show()

                    // After successful login u will move on next page/ activity

//                val i = Intent(this,SecondActivity::class.java)
//                startActivity(i)
                }
            }
        }




    }

}