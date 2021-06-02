package com.example.raylierecipes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.raylierecipes.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this@NewActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Get Data
        val recipeIntent = intent
        val recipeName = recipeIntent.getStringExtra("name")
        val recipeImage = recipeIntent.getStringExtra("img")
        val recipeInfo = recipeIntent.getStringExtra("info")
        val recipeDesc = recipeIntent.getStringExtra("desc")
        binding.recipeName.text = recipeName
        binding.recipeInfo.text = recipeInfo
        binding.description.text = recipeDesc

        if (binding.description.text.toString().contains("\\n")) {
            val newName = (binding.description.text as String?)?.replace("\\n", "\n")
            binding.description.setText(newName)
        }

        Glide.with(this)
            .load(recipeImage)
            .into(binding.RecipeImage)

}   }