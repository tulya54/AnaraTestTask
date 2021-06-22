package com.kmf.anaratesttask.utils

import android.text.TextUtils
import java.util.regex.Pattern

object Validate {

    const val TAG = "Validate"

    var VALID_EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    fun isValidatePhone(phone: String): Boolean {
        return !TextUtils.isEmpty(phone) &&
                phone.length > 10 &&
                TextUtils.isDigitsOnly(phone)
    }

    fun isValidateMail(mail: String): Boolean = VALID_EMAIL_ADDRESS.matcher(mail).matches()
}