package com.example.raylierecipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.raylierecipes.databinding.ActivityMainBinding
import com.example.raylierecipes.model.RecipeData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var recipe1 = RecipeData("Spaghetti", "Yumi", "https://thecozycook.com/wp-content/uploads/2019/08/Bolognese-Sauce-500x500.jpg")
        var recipe2 = RecipeData("Pasta", "Yum", "https://www.indianhealthyrecipes.com/wp-content/uploads/2019/05/masala-pasta.jpg")

        var items: ArrayList<RecipeData> = ArrayList()
        items.add(recipe1)
        items.add(recipe2)

        val adapter = RecyclerViewAdapter(items)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}