package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raylierecipes.databinding.ActivityMainBinding
import com.example.raylierecipes.databinding.ActivityRecyclerViewBinding
import com.example.raylierecipes.model.RecipeData

class RecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
    }
}