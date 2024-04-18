package com.example.myshoppal.ui.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myshoppal.R
import com.example.myshoppal.databinding.DialogProgressBinding
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog
    private var doubleBackToExitPressedOnce = false

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorsnackBarError
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@BaseActivity,
                    R.color.colorsnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

    fun showProgressDialog(text:String){
        mProgressDialog = Dialog(this)
        val dialbinding: DialogProgressBinding = DialogProgressBinding.inflate(layoutInflater)
        mProgressDialog.setContentView(dialbinding.root)

        dialbinding.tvProgressText.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        mProgressDialog.show()
    }

    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

    fun  doubleBackToExit(){
        if (doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true

        Toast.makeText(this,resources.getString(R.string.please_click_back_again_to_exit),
        Toast.LENGTH_SHORT)
            .show()

        @Suppress("DEPRECATION")
        Handler().postDelayed({doubleBackToExitPressedOnce=false},2000)
    }
}