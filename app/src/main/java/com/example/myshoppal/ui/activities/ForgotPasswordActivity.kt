package com.example.myshoppal.ui.activities

import android.os.Bundle
import android.widget.Toast
import com.example.myshoppal.R
import com.example.myshoppal.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : BaseActivity() {
    lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(binding.toolbarForgotPassword)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_arrow_back_24)
        }

        binding.toolbarForgotPassword.setNavigationOnClickListener { onBackPressed() }

        binding.btnSubmit.setOnClickListener {
            val email: String = binding.etEmail.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                //if empty show snackbar
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {
                // if not empty, show progress bar
                showProgressDialog(resources.getString(R.string.please_wait))
                //then send an email
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    //check if task is completed
                    .addOnCompleteListener { task ->
                        //once completed hide progress bar
                        hideProgressDialog()
                        //then if successful show toast and go back to previous screen
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotPasswordActivity,
                                resources.getString(R.string.email_sent_success),
                                Toast.LENGTH_SHORT
                            )
                                .show()

                            finish()
                        } else { //if failed show snackbar
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }
    }
}