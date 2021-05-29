package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raylierecipes.databinding.ActivityMainBinding
import com.example.raylierecipes.databinding.ActivityRecyclerViewBinding
import com.example.raylierecipes.model.RecipeData

class RecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recycler_view)

        val adapter = RecyclerViewAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}