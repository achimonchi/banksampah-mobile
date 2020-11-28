package com.example.banksampah.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.banksampah.R
import com.example.banksampah.utill.Utill
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.dialog_request_sampah.*

class ActivityRequest : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_PERMISSION = 101
    }

    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_request_sampah)
        requestPermission()


        btn_upload.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                CropImage.startPickImageActivity(this)
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        val list = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        ActivityCompat.requestPermissions(this, list, REQUEST_CODE_PERMISSION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            uri = CropImage.getPickImageResultUri(this, data)

            if (Utill.isImageFile(uri, this)) {
                val intent = CropImage.activity(uri).getIntent(this)
                startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            } else {
                Toast.makeText(this, "Hanya Bisa Menampilkan File Gambar", Toast.LENGTH_SHORT)
                    .show()
            }

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val imageResult = CropImage.getActivityResult(data)
            iv_bukti_sampah.visibility = View.VISIBLE
            iv_bukti_sampah.setImageURI(imageResult.uri)
            btn_upload.visibility = View.GONE
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            CropImage.startPickImageActivity(this)
        }
    }

}