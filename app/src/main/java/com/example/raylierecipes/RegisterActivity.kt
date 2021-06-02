package com.example.raylierecipes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)

        binding.btnRegister.isVisible = true
        binding.floatingActionButton.isVisible = true

        binding.floatingActionButton.setOnClickListener {
            finish() //added finish to not layer activities on top of each other
        }

        binding.btnRegister.setOnClickListener{
            registerUser()
        }

    }


    private fun validateRegisteredDetails(): Boolean{
        return when {

            //checking that all required fields are filled before registration

                binding.regEmail.text.toString().trim().isEmpty() -> {
                binding.regEmail.error = "Required"
                Toast.makeText(applicationContext, "Email ID Required", Toast.LENGTH_SHORT).show()
                    false
                }
                binding.regPass.text.toString().trim().isEmpty() -> {
                binding.regPass.error = "Required"
                Toast.makeText(applicationContext, "Password Required", Toast.LENGTH_SHORT).show()
                    false
                }
                binding.regPass.text.toString().length <= 8 ->{
                binding.regPass.error = "Required"
                Toast.makeText(applicationContext, "Password must be between 9 and 12 characters", Toast.LENGTH_SHORT).show()
                    false
                }
                binding.regPass.text.toString().length >= 12 ->{
                binding.regPass.error = "Required"
                Toast.makeText(applicationContext, "Password must be between 9 and 12 characters", Toast.LENGTH_SHORT).show()
                    false
                }
                binding.regPass2.text.toString().trim().isEmpty() -> {
                binding.regPass2.error = "Required"
                Toast.makeText(applicationContext, "Password Confirmation Required", Toast.LENGTH_SHORT).show()
                    false
                }
                binding.regPass.text.toString() != binding.regPass2.text.toString() -> {
                binding.regPass2.error = "Not Matched"
                Toast.makeText(applicationContext, "Password Confirmation Doesn't Match",Toast.LENGTH_SHORT).show()
                    false
                }
                !binding.TaC.isChecked ->{
                binding.TaC.error = "Unchecked"
                Toast.makeText(applicationContext, "Make Sure You Agree With our Terms And Conditions", Toast.LENGTH_SHORT).show()
                    false
                }
                else -> {
                    binding.btnRegister.isVisible = false
                    binding.floatingActionButton.isVisible = false
                    true


                }}}

    private fun registerUser() {

        //make sure that validation process passed
        if (validateRegisteredDetails()){
            val email: String = binding.regEmail.text.toString().trim{ it <= ' '}
            val password: String = binding.regPass.text.toString().trim{ it <= ' '}

            //create instance and register email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener<AuthResult> {task ->

                    //if registration successful
                    if(task.isSuccessful) {

                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(applicationContext, "You have been registered successfully \nyour userid is ${firebaseUser.uid}", Toast.LENGTH_LONG).show()

                        FirebaseAuth.getInstance().signOut()
                        finish()

                    }else{
                        Toast.makeText(applicationContext, "Error Registering your Account \n${task.exception!!.message.toString()}", Toast.LENGTH_LONG).show()
                        binding.btnRegister.isVisible = true
                        binding.floatingActionButton.isVisible = false
                    }
                }
            )
        }
    }
}