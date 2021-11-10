package com.basada.banksampah.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.basada.banksampah.R
import com.basada.banksampah.databinding.ActivityEditPasswordBinding
import com.basada.banksampah.ui.viewmodel.EditPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPasswordActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityEditPasswordBinding
    private val editProfileViewModel: EditPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password)

        dataBinding.apply {
            viewModel = editProfileViewModel
            lifecycleOwner = this@EditPasswordActivity
        }

        editProfileViewModel.action.observe(this, Observer { action ->
            when (action) {
                EditPasswordViewModel.ACTION_EDITPASS_NAVIGATEUP -> onNavigationButtonUp()
                EditPasswordViewModel.ACTION_EDITPASS_FORMEMPTY -> onFormEmpty()
                EditPasswordViewModel.ACTION_EDITPASS_WRONGPASS -> onWrongPass()
                EditPasswordViewModel.ACTION_EDITPASS_PASSCHANGED -> onPasswordChanged()
            }
        })
    }

    private fun onPasswordChanged() {
        Toast.makeText(this, "Berhasil mengubah password !", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun onWrongPass() {
        Toast.makeText(this, "Password lama tidak sesuai", Toast.LENGTH_SHORT).show()
    }

    private fun onFormEmpty() {
        Toast.makeText(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show()
    }

    private fun onNavigationButtonUp() {
        finish()
    }

}