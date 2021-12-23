package com.example.educt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.educt.databinding.ActivityAttendanceBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import org.json.JSONException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class AttendanceActivity : AppCompatActivity() , EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    private lateinit var binding: ActivityAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnScan.setOnClickListener {
            cameraTask()
        }
    }

        private fun hasCameraAccess(): Boolean {
            return EasyPermissions.hasPermissions(this@AttendanceActivity, android.Manifest.permission.CAMERA)
        }

        private fun cameraTask() {

            if (hasCameraAccess()) {

                var qrScanner = IntentIntegrator(this)
                qrScanner.setPrompt("scan a QR code")
                qrScanner.setCameraId(0)
                qrScanner.setOrientationLocked(true)
                qrScanner.setBeepEnabled(true)
                qrScanner.captureActivity = CaptureActivity::class.java
                qrScanner.initiateScan()
            } else {
                EasyPermissions.requestPermissions(
                    this,
                    "This app needs access to your camera so you can take pictures.",
                    123,
                    android.Manifest.permission.CAMERA
                )
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_SHORT).show()

                } else {
                    try {
                        Toast.makeText(this, result.contents.toString(), Toast.LENGTH_LONG).show()
                    } catch (exception: JSONException) {
                        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }

            if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        }

        override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
            if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
                AppSettingsDialog.Builder(this).build().show()
            }
        }

        override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        }

        override fun onRationaleDenied(requestCode: Int) {
        }

        override fun onRationaleAccepted(requestCode: Int) {
        }
}

