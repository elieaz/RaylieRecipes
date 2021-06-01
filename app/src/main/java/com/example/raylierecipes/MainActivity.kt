package com.example.raylierecipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.raylierecipes.databinding.ActivityMainBinding
import com.example.raylierecipes.model.RecipeData
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val TAG: String?= "firestore"
    private lateinit var binding: ActivityMainBinding

    var db = Firebase.firestore
    var items: ArrayList<RecipeData> = ArrayList()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnAddRecipe.setOnClickListener{
            val intent = Intent(this@MainActivity, CreateRecipeActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignout.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        items = arrayListOf()

        db.collection("recipe_id")
            .orderBy("name")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val recipeData = RecipeData(document.data["name"] as String,
                        document.data["info"] as String,document.data["RecipeImage"] as String,
                        document.data["desc"] as String)
                    Log.d(TAG, "${document.id} => ${document.data}")
                    items.add(recipeData)

                }
                val adapter = RecyclerViewAdapter(items)
                recyclerView.adapter= adapter

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

//        EventChangeListener()
    }

//    private fun EventChangeListener() {
//
//        db = FirebaseFirestore.getInstance()
//        db.collection("recipe_ID")
//            .addSnapshotListener(object: EventListener<QuerySnapshot> {
//                override fun onEvent(
//                    value: QuerySnapshot?,
//                    error: FirebaseFirestoreException?
//                ){
//                    if (error != null){
//                        Log.e("Firestore Error", error.message.toString())
//                        return
//                    }
//                    for (dc: DocumentChange in value?.documentChanges!!){
//                        if(dc.type == DocumentChange.Type.ADDED){
//                            items.add(dc.document.toObject(RecipeData::class.java))
//                        }
//                    }
//
//                    adapter.notifyDataSetChanged()
//
//                }
//            }
//            )
//    }
}
