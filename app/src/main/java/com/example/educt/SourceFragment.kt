package com.example.educt

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import java.util.logging.LogManager


class SourceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_source, container, false)

        val takeAttendanceButton = view.findViewById<Button>(R.id.btn_take_attendance)
        val viewAttendanceButton = view.findViewById<Button>(R.id.btn_view_attendance)

        val logoutButton = view.findViewById<Button>(R.id.btn_logout)

        takeAttendanceButton.setOnClickListener { findNavController()
            .navigate(R.id.action_sourceFragment_to_takeAttendanceFragment) }

        viewAttendanceButton.setOnClickListener { findNavController()
            .navigate(R.id.action_sourceFragment_to_viewAttendanceFragment)}


        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return view
    }


}