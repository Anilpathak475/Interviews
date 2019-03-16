package com.test.todo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appify.network.DataCallback
import com.appify.network.models.ApiError
import com.appify.network.models.Register
import com.appify.network.store.RegisterStore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        button.setOnClickListener { onRegister() }
    }


    private fun onRegister() {
        if (validate()) {
            val register = Register(firstName.text.toString(), lastName.text.toString(), phoneNumber.text.toString(), emailRegister.text.toString(), confirmPassword.text.toString())
            registerUser(register)
        }
    }

    private fun registerUser(register: Register) {
        hideKeyboard()
        showProgress(true)
        RegisterStore.instance.register(register, object : DataCallback<Register> {
            override fun onSuccess(t: Register?) {
                showProgress(false)
                if (t != null) {
                    val intent = Intent(this@RegisterActivity, LoginActivity
                    ::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(error: ApiError) {
                showProgress(false)
                showMessage(error.message())
            }
        })

    }

    private fun validate(): Boolean {
        if (firstName.text.toString().isEmpty()) {
            showMessage("FirstName can not be blank")
            return false
        }
        if (lastName.text.toString().isEmpty()) {
            showMessage("LastName can not be blank")
            return false
        }
        if (emailRegister.text.toString().isEmpty()) {
            showMessage("Email can not be blank")
            return false
        }
        if (phoneNumber.text.toString().isEmpty()) {
            showMessage("Phone Number can not be blank")
            return false
        }
        if (newPassword.text.toString().isEmpty()) {
            showMessage("Password can not be blank")
            return false
        }
        if (confirmPassword.text.toString().isEmpty()) {
            showMessage("Password can not be blank")
            return false
        }

        if (confirmPassword.text.toString().isEmpty()) {
            showMessage("Password can not be blank")
            return false
        }
        return true
    }


    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            layoutRegister.visibility = if (show) View.GONE else View.VISIBLE
            layoutRegister.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            layoutRegister.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            register_progress.visibility = if (show) View.VISIBLE else View.GONE
            register_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            register_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            register_progress.visibility = if (show) View.VISIBLE else View.GONE
            layoutRegister.visibility = if (show) View.GONE else View.VISIBLE
        }
    }


    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
