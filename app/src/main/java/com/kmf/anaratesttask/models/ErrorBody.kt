package com.kmf.anaratesttask.models

data class ErrorBody(val text: ErrorText)

data class ErrorText(val code: Int, val type: String, val message: String)