package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityCreateRecipeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateRecipeActivity : AppCompatActivity() {

    private val TAG: String?= "createRep"
    private lateinit var binding: ActivityCreateRecipeBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_create_recipe)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_recipe)

        binding.btnCreate.setOnClickListener{
            val recipe = hashMapOf(
                "name" to binding.RecipeName.text.toString(),
                "RecipeImage" to binding.image.text.toString(),
                "info" to binding.info.text.toString()
            )
            db.collection("recipe_id")
                .add(recipe)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

// Add a new document with a generated ID


    }
}