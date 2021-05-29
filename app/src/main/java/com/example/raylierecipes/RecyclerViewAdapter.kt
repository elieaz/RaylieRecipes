package com.example.raylierecipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.raylierecipes.databinding.ActivityRecyclerViewBinding
import com.example.raylierecipes.databinding.RecipeItemBinding
import com.example.raylierecipes.model.RecipeData

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var RecipeList = emptyList<RecipeData>()


    inner class ViewHolder(val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeData) {
            with(binding) {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false))
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(layoutInflater)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
    //override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // val entry = RecipeList[position]
        holder.binding.itemView.recipeName.text = entry.name.toString()
        holder.itemView.recipeInfo.text = entry.info.toString()
        //holder.itemView.studentImage.setImageResource(entry.studentPictureIndex)
        holder.itemView.recipeImage.load(entry.img)

        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context, "Position: ${position + 1}", Toast.LENGTH_SHORT).show()
        }
    }

    fun setData(recipes:List<RecipeData>) {
        RecipeList = recipes
        notifyDataSetChanged()
    }
}