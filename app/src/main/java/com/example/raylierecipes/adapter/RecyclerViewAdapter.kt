package com.example.raylierecipes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.raylierecipes.R
import com.example.raylierecipes.model.RecipeData
//import coil.load
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var RecipeList = emptyList<RecipeData>()

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item,parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = RecipeList[position]
        holder.itemView.recipe_name.text = entry.name.toString()
        holder.itemView.recipe_info.text = entry.info.toString()
        //holder.itemView.recipe_img.setImageResource(entry.RecipeImage)
       // holder.itemView.recipe_img.load(entry.RecipeImage)

        holder.itemView.setOnClickListener(){
            Toast.makeText(holder.itemView.context, "Position: ${position + 1}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }

    fun setData(recipes:List<RecipeData>) {
        RecipeList = recipes
        notifyDataSetChanged()
    }

}




