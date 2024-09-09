package com.project.agreetotermsofuse

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.agreetotermsofuse.databinding.ActivityMainBinding
import com.project.agreetotermsofuse.databinding.BottomSheetLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AgreementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
    }

    fun openBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val sheetBinding: BottomSheetLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.bottom_sheet_layout, null, true)

        sheetBinding.agreementViewModel = viewModel
        sheetBinding.lifecycleOwner = this
        bottomSheetDialog.setContentView(sheetBinding.root)
        bottomSheetDialog.show()

        viewModel.allAgreeList.observe(this) {
            val buttonColor = if (viewModel.isAllAgreed()) {
                ContextCompat.getColor(this, R.color.blue)
            } else {
                ContextCompat.getColor(this, R.color.gray)
            }

            sheetBinding.buttonComplete.setBackgroundColor(buttonColor)

            sheetBinding.buttonComplete.setOnClickListener {
                if (buttonColor == ContextCompat.getColor(this, R.color.blue)) {
                    bottomSheetDialog.dismiss()
                }
            }

        }
    }
}