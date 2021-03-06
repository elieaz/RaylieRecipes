package com.example.raylierecipes

import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.raylierecipes.databinding.RecipeItemBinding
import com.example.raylierecipes.model.RecipeData


class RecyclerViewAdapter(private var recipeList: List<RecipeData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeData) {
            with(binding) {
                binding.recipeName.text = item.name
                binding.recipeInfo.text = item.info


                Glide.with(itemView)
                    .load(item.RecipeImage)
                    .into(binding.recipeImg)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, NewActivity::class.java)
                    intent.putExtra("img", item.RecipeImage)
                    intent.putExtra("name", item.name)
                    intent.putExtra("info", item.info)
                    intent.putExtra("desc", item.desc)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val newList = recipeList[position]

        //return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false))
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(layoutInflater)

        return ViewHolder(binding)
    }



    override fun getItemCount(): Int {
        return recipeList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(recipeList[position])

    fun setData(recipes:List<RecipeData>) {
        recipeList = recipes
        notifyDataSetChanged()
    }

}
