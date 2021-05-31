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
//        val img = newList.RecipeImage
//        val name = newList.name
//        val info = newList.info



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

private fun getSpannableString(input: String): CharSequence? {
    val ss = SpannableString(input)
    var offset = 0
    do {
        var lineEnd = input.indexOf('\n', offset + 1)
        if (lineEnd == -1) lineEnd = input.length
        val line = input.substring(offset, lineEnd)
//        ss.setSpan(ForegroundColorSpan(-0xff0100), offset, lineEnd, 0)
        ss.setSpan(BulletSpan(15, Color.TRANSPARENT), offset, lineEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    } while (input.indexOf('\n', offset + 1).also { offset = it } !== -1)
    return ss
}