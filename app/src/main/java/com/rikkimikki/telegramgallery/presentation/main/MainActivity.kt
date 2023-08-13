package com.rikkimikki.telegramgallery.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rikkimikki.telegramgallery.domain.entity.AuthState
import com.rikkimikki.telegramgallery.presentation.login.AuthorizeScreen
import com.rikkimikki.telegramgallery.presentation.login.LoginScreen
import com.rikkimikki.telegramgallery.ui.theme.TelegramGalleryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelegramGalleryTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.collectAsState(AuthState.Initial)

                var isOvercome by rememberSaveable { mutableStateOf(false) }

                when (authState.value) {
                    AuthState.LoggedIn -> {
                        MainScreen()
                    }
                    AuthState.EnterCode -> {
                        AuthorizeScreen(AuthState.EnterCode){
                            viewModel.sendCode(it)
                        }
                    }
                    AuthState.EnterPhone -> {
                        if (isOvercome){
                            AuthorizeScreen(AuthState.EnterPhone){
                                viewModel.sendPhone(it)
                            }
                        } else {
                            LoginScreen {
                                isOvercome = true
                            }
                        }
                    }
                    AuthState.EnterPassword -> {
                        AuthorizeScreen(AuthState.EnterPassword){
                            viewModel.sendPassword(it)
                        }
                    }
                    AuthState.Waiting -> {
                        Text(text = "Waiting")
                    }
                    AuthState.Initial -> {
                        Text(text = "Init")
                        viewModel.performAuthResult()
                    }
                }
            }
        }
    }
}
