package com.example.banksampah.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivityRequestBinding
import com.example.banksampah.model.entity.SampahKategoryItem
import com.example.banksampah.ui.viewmodel.RequestViewModel
import com.example.banksampah.utill.Utill
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_request.*

@AndroidEntryPoint
class RequestActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_PERMISSION = 101
        const val EXTRA_ITEM = "extra_item"
    }

    var uri: Uri? = null

    private val requestViewModel: RequestViewModel by viewModels()
    private lateinit var dataBinding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_request)

        val item: SampahKategoryItem? = intent.getParcelableExtra(EXTRA_ITEM)

        dataBinding.apply {
            lifecycleOwner = this@RequestActivity
            viewModel = requestViewModel
            itemSampah = item
        }

        requestViewModel.action.observe(this, Observer { action ->
            when (action) {
                RequestViewModel.ACTION_REQUEST_SUCCESS -> onRequestSuccess()
                RequestViewModel.ACTION_REQUEST_BTN_UPLOAD -> btnUploadOnClick()
                RequestViewModel.ACTION_REQUEST_TIMEOUT -> onRequestTimeout()
                RequestViewModel.ACTION_REQUEST_URI_ISEMPTY -> uriIsEmpty()
            }
        })
    }

    private fun uriIsEmpty() {
        Toast.makeText(this, "Pilih Gambar terlebih dahulu", Toast.LENGTH_SHORT).show()
    }

    private fun onRequestTimeout() {
        Toast.makeText(this, "Gagal Memesan Sampah", Toast.LENGTH_SHORT).show()
    }

    private fun onRequestSuccess() {
        Toast.makeText(this, "Berhasil Memesan Sampah", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun btnUploadOnClick() {
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

            requestViewModel.isUploaded.value = true
            dataBinding.uri = imageResult.uri
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION
//            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            CropImage.startPickImageActivity(this)
        }
    }

}