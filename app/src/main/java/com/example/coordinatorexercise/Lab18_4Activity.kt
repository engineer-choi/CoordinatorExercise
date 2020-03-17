package com.example.coordinatorexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_lab18_4.*
import kotlinx.android.synthetic.main.lab4_modal_sheet.*

class Lab18_4Activity : AppCompatActivity(), View.OnClickListener {

    lateinit var persistentBottomSeet : BottomSheetBehavior<View>
    lateinit var modalBottomSeet : BottomSheetDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab18_4)
        init()
    }

    private fun init(){
        lab4_button.setOnClickListener(this)
        initPersistentBottomSheet()
    }
    override fun onClick(v: View?) {
        createDialog()
    }
    private fun createDialog(){
        val list = mutableListOf<DataVO>()

        val vo = DataVO()
        vo.title = "Keep"
        vo.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo)
        val vo1 = DataVO()
        val vo2 = DataVO()
        val vo3 = DataVO()
        vo1.title = "Inbox"
        vo1.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo1)
        vo2.title = "asfd"
        vo2.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo2)
        vo3.title = "sfasdx"
        vo3.image = ResourcesCompat.getDrawable(resources,R.drawable.appbar_image,null)
        list.add(vo3)

        val adapter = Lab4RecyclerViewAdapter(list)
        val view = layoutInflater.inflate(R.layout.lab4_modal_sheet,null)
        val rv = view.findViewById<RecyclerView>(R.id.lab4_recyclerView)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv.adapter = adapter

        modalBottomSeet = BottomSheetDialog(this)
        modalBottomSeet.setContentView(view)
        modalBottomSeet.show()
    }
    private fun initPersistentBottomSheet(){
        val bottomSheet = lab4_bottom_sheet
        persistentBottomSeet = BottomSheetBehavior.from(bottomSheet)
    }
}
