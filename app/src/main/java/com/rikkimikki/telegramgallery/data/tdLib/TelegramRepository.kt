package com.rikkimikki.telegramgallery.data.tdLib

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.rikkimikki.telegramgallery.data.telegramCore.core.TelegramFlow
import com.rikkimikki.telegramgallery.data.telegramCore.core.TelegramException
import com.rikkimikki.telegramgallery.data.telegramCore.core.ResultHandlerStateFlow
import com.rikkimikki.telegramgallery.data.telegramCore.coroutines.*
import com.rikkimikki.telegramgallery.data.telegramCore.extensions.ChatKtx
import com.rikkimikki.telegramgallery.data.telegramCore.extensions.UserKtx
import com.rikkimikki.telegramgallery.data.telegramCore.flows.*
import com.rikkimikki.telegramgallery.domain.AuthState
import com.rikkimikki.telegramgallery.domain.TdRepository
import org.drinkless.td.libcore.telegram.TdApi
import org.drinkless.td.libcore.telegram.TdApi.Chat
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random


object TelegramRepository : UserKtx, ChatKtx , TdRepository {
    override val api: TelegramFlow = TelegramFlow()

    val authFlow = api.authorizationStateFlow()
        .onEach {
            checkRequiredParams(it)
        }
        .map {
            when (it) {
                is TdApi.AuthorizationStateReady -> AuthState.LoggedIn
                is TdApi.AuthorizationStateWaitCode -> AuthState.EnterCode
                is TdApi.AuthorizationStateWaitPassword -> AuthState.EnterPassword(it.passwordHint)
                is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.EnterPhone
                else -> null
            }
        }

    private suspend fun checkRequiredParams(state: TdApi.AuthorizationState?) {
        when (state) {
            is TdApi.AuthorizationStateWaitTdlibParameters -> {
                api.setTdlibParameters(TelegramCredentials.parameters)
            }
            is TdApi.AuthorizationStateWaitEncryptionKey ->{
                api.checkDatabaseEncryptionKey(null)
            }
            is TdApi.AuthorizationStateReady ->{
                println("ready for use")
            }
        }
    }



    suspend fun sendPhone(phone: String) {
        api.setAuthenticationPhoneNumber(phone, null)
    }

    suspend fun sendCode(code: String) {
        api.checkAuthenticationCode(code)
    }

    suspend fun sendPassword(password: String) {
        api.checkAuthenticationPassword(
            password
        )
    }

    suspend fun exit() {
        api.logOut()
    }
}