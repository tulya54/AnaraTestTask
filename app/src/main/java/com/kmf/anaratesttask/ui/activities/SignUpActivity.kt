package com.kmf.anaratesttask.ui.activities

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import com.kmf.anaratesttask.R
import com.kmf.anaratesttask.base_classes.BaseActivity
import com.kmf.anaratesttask.models.ApiResponse
import com.kmf.anaratesttask.models.UserInfo
import com.kmf.anaratesttask.ui.fragments.StatusFragment
import com.kmf.anaratesttask.utils.Validate
import com.kmf.anaratesttask.utils.enumeration.Status
import com.kmf.anaratesttask.viewmodels.SignUpViewModel
import okhttp3.RequestBody
import org.json.JSONObject
import org.koin.android.ext.android.inject
import timber.log.Timber

class SignUpActivity: BaseActivity(R.layout.activity_sign_up) {

    companion object {
        private const val TAG = "SignUpActivity"
    }

    //  DI object
    val viewModel: SignUpViewModel by inject()
    //  Widgets
    private var etLogin: EditText? = null
    private var etName: EditText? = null
    private var etSurname: EditText? = null
    private var etMail: EditText? = null
    private var etPassword: EditText? = null
    private var etPhone: EditText? = null
    private var tvStatus: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun initViews() {
        //  Find views in layout
        etLogin = findViewById(R.id.etLogin)
        etName = findViewById(R.id.etName)
        etSurname = findViewById(R.id.etSurname)
        etMail = findViewById(R.id.etMail)
        etPassword = findViewById(R.id.etPassword)
        etPhone = findViewById(R.id.etPhone)
        tvStatus = findViewById(R.id.tvStatus)
        tvStatus?.setOnClickListener {
            val fragment = StatusFragment()
            fragment.show(supportFragmentManager, null)
            fragment.setOnItemClickListener(object :StatusFragment.OnItemLickListener {
                override fun onItemClick(index: Int) {
                    tvStatus?.text = index.toString()
                }
            })
        }
        progressBar = findViewById(R.id.progressBar)
        //  Init listener button sign up
        findViewById<Button>(R.id.btnStart)
            .setOnClickListener {
                onSignUo()
            }
        viewModel.getResponse().observe(this, Observer {
            onResponse(it)
        })
    }

    private fun onSignUo() {
        //  Validate phone number and mail
        var isValidatePhone = false
        var isValidateMail = false
        etPhone?.let {
            isValidatePhone = Validate.isValidatePhone(it.text.toString())
        }
        etMail?.let {
            isValidateMail = Validate.isValidateMail(it.text.toString())
        }
        if (!isValidatePhone) return
        if (!isValidateMail) return
        try {
            val value = UserInfo(
                username = etLogin?.text.toString(),
                firstName = etName?.text.toString(),
                lastName = etSurname?.text.toString(),
                email = etMail?.text.toString(),
                password = etPassword?.text.toString(),
                phone = etPhone?.text.toString(),
                userStatus = tvStatus?.text.toString().toInt()
            )
            viewModel.signUp(value)
        } catch (e: Exception) {
            Timber.tag(TAG).d(e)
        }
    }

    private fun onResponse(apiResponse: ApiResponse) {
        isProgressBarShow(apiResponse.status == Status.LOADING)
        when(apiResponse.status) {
            Status.SUCCESS -> {
                apiResponse.data?.let {
                    Timber.tag(TAG).d(it.message())
                }
                toast(R.string.user_add)
            }
            Status.ERROR -> {
                apiResponse.error?.let {
                    Timber.tag(TAG).d(it.message())
                }
                toast(R.string.user_not_add)
            }
            Status.THROWABLE -> {
                Timber.tag(TAG).d(apiResponse.throwabl)
                toast(R.string.user_add)
            }
        }
    }

    private fun isProgressBarShow(isShow: Boolean) {
        progressBar?.let {
            it.visibility = if (isShow) View.VISIBLE else View.GONE
        }
    }
}