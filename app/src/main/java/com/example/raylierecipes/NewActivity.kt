package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.raylierecipes.databinding.ActivityMainBinding
import com.example.raylierecipes.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new)

        //Get Data
        val recipeIntent = intent
        val recipeName = recipeIntent.getStringExtra("name")
        val recipeImage = recipeIntent.getStringExtra("img")
        val recipeInfo = recipeIntent.getStringExtra("info")
        binding.recipeName.text = recipeName
        binding.recipeInfo.text = recipeInfo
        Glide.with(this)
            .load(recipeImage)
            .into(binding.RecipeImage)

        //actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "New Activity"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}