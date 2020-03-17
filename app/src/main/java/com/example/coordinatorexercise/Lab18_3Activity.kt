package com.example.coordinatorexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Lab18_3Activity : AppCompatActivity() {
    var coordinatorLayout: CoordinatorLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab18_3)
        val toolBar: Toolbar = findViewById(R.id.lab3_toolbar) as Toolbar
        setSupportActionBar(toolBar)
        val recyclerView: RecyclerView = findViewById(R.id.lab3_recycler) as RecyclerView
        val list: MutableList<String> =
            ArrayList()
        for (i in 0..19) {
            list.add("Item=$i")
        }
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(MyAdapter(list))
        coordinatorLayout = findViewById(R.id.lab3_coordinator) as CoordinatorLayout?

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_lab3, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private inner class MyAdapter(private val list: List<String>) :
        RecyclerView.Adapter<MyViewHolder?>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
            val text = list[position]
            viewHolder.title.text = text
        }


        override fun getItemCount(): Int {
            return list.size
        }

    }

    private inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView

        init {
            title = itemView.findViewById<View>(android.R.id.text1) as TextView
        }
    }
}
