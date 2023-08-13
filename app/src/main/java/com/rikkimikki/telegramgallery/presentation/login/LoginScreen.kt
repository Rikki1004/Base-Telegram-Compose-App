package com.rikkimikki.telegramgallery.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rikkimikki.telegramgallery.R
import com.rikkimikki.telegramgallery.ui.theme.DarkBlue

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.tg_logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue,
                    contentColor = Color.White
                ),
                onClick = { onLoginClick() }
            ) {
                Text(text = stringResource(R.string.button_login))
            }
        }
    }
}
