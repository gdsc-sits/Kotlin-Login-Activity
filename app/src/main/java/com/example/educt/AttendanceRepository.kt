package com.example.educt

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.educt.db.Attendance
import com.example.educt.db.AttendanceDao

class AttendanceRepository (private val attendanceDao: AttendanceDao) {

    val allAttendance: LiveData<List<Attendance>> = attendanceDao.getAllAttendance()

    suspend fun insert(attendance: Attendance){
        attendanceDao.insert(attendance)
    }

    suspend fun delete(attendance: Attendance){
        attendanceDao.delete(attendance)
    }

}