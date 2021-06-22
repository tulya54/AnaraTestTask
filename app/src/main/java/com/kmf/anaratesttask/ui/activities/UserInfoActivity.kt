package com.kmf.anaratesttask.ui.activities

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.kmf.anaratesttask.R
import com.kmf.anaratesttask.base_classes.BaseActivity
import com.kmf.anaratesttask.db.UserInfoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class UserInfoActivity: BaseActivity(R.layout.activity_user_info) {

    companion object {
        private const val TAG = "UserInfoActivity"
    }

    override fun initViews() {
        coroutine.launch(Dispatchers.IO) {
            val value = db.getUserInfo()
            coroutine.launch(Dispatchers.Main) {
                loadDataInDB(value)
            }
        }
    }

    private fun loadDataInDB(value: UserInfoEntity?) {
        value?.let { data ->
            Timber.tag(TAG).d("Value is not empty")
            data.username?.let {
                findViewById<TextView>(R.id.tvUsername)?.let { tv ->
                    setData(tv, R.string.login, it)
                }
            }
            data.firstName?.let {
                findViewById<TextView>(R.id.tvFirstName)?.let { tv ->
                    setData(tv, R.string.name, it)
                }
            }
            data.lastName?.let {
                findViewById<TextView>(R.id.tvLastName)?.let { tv ->
                    setData(tv, R.string.surname, it)
                }
            }
            data.email?.let {
                findViewById<TextView>(R.id.tvEmail)?.let { tv ->
                    setData(tv, R.string.mail, it)
                }
            }
            data.password?.let {
                findViewById<TextView>(R.id.tvPassword)?.let { tv ->
                    setData(tv, R.string.password, it)
                }
            }
            data.phone?.let {
                findViewById<TextView>(R.id.tvPhone)?.let { tv ->
                    setData(tv, R.string.phone, it)
                }
            }
            data.userStatus?.let {
                findViewById<TextView>(R.id.tvUserStatus)?.let { tv ->
                    setData(tv, R.string.status, it.toString())
                }
            }
            coroutine.launch(Dispatchers.IO) {
                db.delete(value)
            }
        }
    }

    private fun setData(tv: TextView, @StringRes resText: Int, value: String) {
        val text = "${getStr(resText)}: $value"
        tv.visibility = View.VISIBLE
        tv.text = text
    }
}