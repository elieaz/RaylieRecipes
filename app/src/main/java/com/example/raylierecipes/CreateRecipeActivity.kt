package com.example.raylierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityCreateRecipeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateRecipeActivity : AppCompatActivity() {

    private val TAG: String = "createRep"
    private lateinit var binding: ActivityCreateRecipeBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_create_recipe)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_recipe)
        binding.btnCreate.isVisible = true
        binding.floatingActionButton2.isVisible = true

        binding.floatingActionButton2.setOnClickListener {
            finish()
            val intent = Intent(this@CreateRecipeActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnCreate.setOnClickListener{
            binding.btnCreate.isVisible = false
            binding.floatingActionButton2.isVisible = false
            val recipe = hashMapOf(
                "name" to binding.RecipeName.text.toString(),
                "RecipeImage" to binding.image.text.toString(),
                "info" to binding.info.text.toString(),
                "desc" to binding.inputSteps.text.toString()
            )
            db.collection("recipe_id")
                .add(recipe)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")

                    Toast.makeText(applicationContext, "Recipe Successfully Added", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@CreateRecipeActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    binding.btnCreate.isVisible = true
                    binding.floatingActionButton2.isVisible = true
                    Toast.makeText(applicationContext, "Error \nRecipe Was Not Added", Toast.LENGTH_SHORT).show()
                }
        }

// Add a new document with a generated ID
        //actionbar
//        val actionbar = supportActionBar
//        actionbar!!.title = "New Activity"
//        actionbar.setDisplayHomeAsUpEnabled(true)
//        actionbar.setDisplayHomeAsUpEnabled(true)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//
//    }
}