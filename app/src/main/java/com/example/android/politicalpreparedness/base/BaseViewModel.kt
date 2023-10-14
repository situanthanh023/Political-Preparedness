package com.example.android.politicalpreparedness.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {
    val showSnackBar = MutableLiveData<String>()
    val showSnackBarInt = MutableLiveData<Int>()
}