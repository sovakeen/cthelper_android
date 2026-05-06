package com.example.cthelper.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(

): ViewModel() {
    fun sampleFun(): String {
        return "ViewModel produced"
    }
}
