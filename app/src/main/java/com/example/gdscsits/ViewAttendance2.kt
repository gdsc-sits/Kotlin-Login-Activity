package com.example.educt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educt.db.Attendance
import kotlinx.android.synthetic.main.activity_view_attendance2.*

class ViewAttendance2 : AppCompatActivity(){

lateinit var viewModel: AttendanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_attendance2)


        recyclerview.layoutManager=LinearLayoutManager(this)
        val adapter=AttendanceRVAdapter(this)
        recyclerview.adapter=adapter


        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(AttendanceViewModel::class.java)
        viewModel.allAttendance.observe(this, Observer { list->
            list?.let {
                adapter.updateList(it )
            }

        })

    }


}