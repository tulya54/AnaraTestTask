package com.kmf.anaratesttask.ui.activities

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.kmf.anaratesttask.R
import com.kmf.anaratesttask.base_classes.BaseActivity
import com.kmf.anaratesttask.db.UserInfoEntity
import com.kmf.anaratesttask.models.ApiResponse
import com.kmf.anaratesttask.models.UserInfo
import com.kmf.anaratesttask.utils.enumeration.Status
import com.kmf.anaratesttask.viewmodels.UserInfoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class FindUserActivity: BaseActivity(R.layout.activity_find_user) {

    companion object {
        const val TAG = "UserInfoActivity"
    }

    //  DI object
    val viewModel: UserInfoViewModel by inject()
    //  Widgets
    private var etUserName: EditText? = null
    private var progressBar: ProgressBar? = null

    override fun initViews() {
        etUserName = findViewById(R.id.etUserName)
        progressBar = findViewById(R.id.progressBar)
        findViewById<Button>(R.id.btnFindUserName)
            .setOnClickListener {
                etUserName?.let {
                    val text = it.text.toString()
                    if (text.isNotEmpty()) {
                        onFindUser(text)
                    } else {
                        toast(R.string.enter_user_name)
                    }
                }
            }
        //  Init listener for button find user
        viewModel.getResponse().observe(this, Observer {
            onResponse(it)
        })
    }

    private fun onResponse(apiResponse: ApiResponse) {
        when(apiResponse.status) {
            Status.LOADING -> {
                isProgressBarShow(true)
            }
            Status.SUCCESS -> {
                isProgressBarShow(false)
                apiResponse.data?.let {
                    Timber.tag(TAG).d(it.message())
                    try {
                        launchUserInfo(it.body() as UserInfo?)
                    } catch (e: Exception) {
                        Timber.tag(TAG).d(e)
                    }
                }
            }
            Status.ERROR -> {
                isProgressBarShow(false)
                apiResponse.error?.let {
                    Timber.tag(TAG).d(it.message())
                    try {

                    } catch (e: Exception) {
                        Timber.tag(TAG).d(e)
                    }
                }
                toast(R.string.user_not_found)
            }
            Status.THROWABLE -> {
                Timber.tag(TAG).d(apiResponse.throwabl)
                isProgressBarShow(false)
            }
        }
    }

    private fun onFindUser(userName: String) = viewModel.getUser(userName)

    private fun isProgressBarShow(isShow: Boolean) {
        progressBar?.let {
            it.visibility = if (isShow) View.VISIBLE else View.GONE
        }
        findViewById<Button>(R.id.btnFindUserName).isEnabled = !isShow
    }

    private fun launchUserInfo(data: UserInfo?) {
        data?.let {
            val value = UserInfoEntity(
                username = it.username,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
                password = it.password,
                phone = it.phone,
                userStatus = it.userStatus
            )
            coroutine.launch {
                db.insert(value)
                delay(2000)
                startActivity(Intent(this@FindUserActivity, UserInfoActivity::class.java))
            }
        }
    }
}