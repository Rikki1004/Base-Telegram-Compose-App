package com.rikkimikki.telegramgallery.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rikkimikki.telegramgallery.data.tdLib.TelegramRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = TelegramRepository

    val authState = repository.getAuthStateFlow()

    fun performAuthResult() {
        repository.checkAuthState()
    }

    fun sendPhone(phone: String) {
        viewModelScope.launch {
            repository.sendPhone(phone)
        }
    }

    fun sendCode(code: String) {
        viewModelScope.launch {
            repository.sendCode(code)
        }
    }

    fun sendPassword(password: String) {
        viewModelScope.launch {
            repository.sendPassword(password)
        }
    }
}
