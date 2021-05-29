package com.example.raylierecipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.raylierecipes.databinding.ActivityRecyclerViewBinding
import com.example.raylierecipes.databinding.RecipeItemBinding
import com.example.raylierecipes.model.RecipeData

class RecyclerViewAdapter(private var recipeList: List<RecipeData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeData) {
            with(binding) { binding.recipeName.text = item.name
                            binding.recipeInfo.text = item.info
                            Glide.with(itemView)
                                .load(item.RecipeImage)
                                .into(binding.recipeImg)
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
        return recipeList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(recipeList[position])
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val entry = RecipeList[position]
//        holder.binding.itemView.recipeName.text = entry.name.toString()
//        holder.itemView.recipeInfo.text = entry.info.toString()
//        holder.itemView.studentImage.setImageResource(entry.studentPictureIndex)
//        holder.itemView.recipeImage.load(entry.img)
//
//        holder.itemView.setOnClickListener(){
//            Toast.makeText(holder.itemView.context, "Position: ${position + 1}", Toast.LENGTH_SHORT).show()



    fun setData(recipes:List<RecipeData>) {
        recipeList = recipes
        notifyDataSetChanged()
    }

}