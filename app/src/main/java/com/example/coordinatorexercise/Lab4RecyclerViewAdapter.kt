package com.example.coordinatorexercise

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Lab4RecyclerViewAdapter(private val list: List<DataVO>) :
    RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.lab4_sheet_row, parent, false)
        return ItemHolder(root)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val vo = list[position]
        holder.textView.text = vo.title
        holder.imageView.setImageDrawable(vo.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class ItemHolder(root: View?) :
    RecyclerView.ViewHolder(root!!) {
    var textView: TextView
    var imageView: ImageView

    init {
        imageView =
            itemView.findViewById<View>(R.id.lab4_sheet_row_imageView) as ImageView
        textView =
            itemView.findViewById<View>(R.id.lab4_sheet_row_textView) as TextView
    }
}

class DataVO {
    var title: String? = null
    var image: Drawable? = null
}
