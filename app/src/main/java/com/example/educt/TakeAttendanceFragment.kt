package com.example.educt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_take_attendance.*


class TakeAttendanceFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_take_attendance, container, false)

        val nxt_button = view.findViewById<Button>(R.id.nxt_button)

        nxt_button.setOnClickListener { findNavController()
            .navigate(R.id.action_takeAttendanceFragment_to_blankFragment) }
        return view

    }

}