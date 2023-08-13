package com.rikkimikki.telegramgallery.data.tdLib


import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import com.rikkimikki.telegramgallery.data.telegramCore.core.TelegramFlow
import com.rikkimikki.telegramgallery.data.telegramCore.coroutines.*
import com.rikkimikki.telegramgallery.data.telegramCore.extensions.ChatKtx
import com.rikkimikki.telegramgallery.data.telegramCore.extensions.UserKtx
import com.rikkimikki.telegramgallery.data.telegramCore.flows.*
import com.rikkimikki.telegramgallery.domain.entity.AuthState
import com.rikkimikki.telegramgallery.domain.TdRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.takeWhile
import org.drinkless.td.libcore.telegram.TdApi


object TelegramRepository : UserKtx, ChatKtx , TdRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    override val api: TelegramFlow = TelegramFlow()

    private val authFlow = api.authorizationStateFlow()
        .onEach {
            println("state: "+it.toString())
            checkRequiredParams(it)
        }
        .map {
            when (it) {
                is TdApi.AuthorizationStateReady -> AuthState.LoggedIn
                is TdApi.AuthorizationStateWaitCode -> AuthState.EnterCode
                is TdApi.AuthorizationStateWaitPassword -> AuthState.EnterPassword
                is TdApi.AuthorizationStateWaitPhoneNumber -> AuthState.EnterPhone
                else -> AuthState.Waiting
            }
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Lazily,
            initialValue = AuthState.Initial
        )

    private suspend fun checkRequiredParams(state: TdApi.AuthorizationState?) {
        when (state) {
            is TdApi.AuthorizationStateWaitTdlibParameters -> {
                api.setTdlibParameters(TelegramCredentials.parameters)
            }
            is TdApi.AuthorizationStateWaitEncryptionKey ->{
                api.checkDatabaseEncryptionKey(ByteArray(0))
            }
            is TdApi.AuthorizationStateReady ->{
                println("ready for use")
            }
        }
    }

    //fun getAuthStateFlow(): StateFlow<AuthState> = authFlow
    fun getAuthStateFlow(): Flow<AuthState> = authFlow

    fun checkAuthState() {
        api.attachClient()
    }

    suspend fun sendPhone(phone: String) {
        api.setAuthenticationPhoneNumber(phone, null)
    }

    suspend fun sendCode(code: String) {
        api.checkAuthenticationCode(code)
    }

    suspend fun sendPassword(password: String) {
        api.checkAuthenticationPassword(password)
    }

    suspend fun exit() {
        api.logOut()
    }
}