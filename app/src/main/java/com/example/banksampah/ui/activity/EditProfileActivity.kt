package com.example.banksampah.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.banksampah.R
import com.example.banksampah.databinding.ActivityEditProfileBinding
import com.example.banksampah.ui.viewmodel.EditProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {

    private val editProfileViewModel: EditProfileViewModel by viewModels()
    private lateinit var dataBinding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

        dataBinding.apply {
            lifecycleOwner = this@EditProfileActivity
            viewModel = editProfileViewModel
        }

        editProfileViewModel.apply {
            action.observe(this@EditProfileActivity, Observer { action ->
                when (action) {
                    EditProfileViewModel.ACTION_EDIT_BTN_PASSCLICK -> btnUbahOnClick()
                    EditProfileViewModel.ACTION_EDIT_SUCCESS -> editSuccess()
                    EditProfileViewModel.ACTION_EDIT_TIMEOUT -> editFailed()
                    EditProfileViewModel.ACTION_EDIT_NAVIGATEUP -> onBackButtonPress()
                }
            })
            setProfile()
        }
    }

    private fun onBackButtonPress() {
        finish()
    }

    private fun editFailed() {
        Toast.makeText(this, "Gagal Mengubah Profil!, Coba lagi", Toast.LENGTH_SHORT).show()
    }

    private fun editSuccess() {
        Toast.makeText(this, "Berhasil Mengubah Profil!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun btnUbahOnClick() {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }
}