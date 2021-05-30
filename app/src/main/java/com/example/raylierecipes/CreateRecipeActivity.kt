package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.raylierecipes.databinding.ActivityCreateRecipeBinding

class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_create_recipe)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_create_recipe)

    }
}