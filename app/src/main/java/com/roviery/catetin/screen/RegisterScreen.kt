package com.roviery.catetin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.roviery.catetin.ui.theme.CatetinTheme

@Composable
fun RegisterScreen(navController: NavController) {
    CatetinTheme {
        val constraints = ConstraintSet {
            val appName = createRefFor("appName")
            val username = createRefFor("username")
            val email = createRefFor("email")
            val password = createRefFor("password")
            val loginBtn = createRefFor("loginBtn")
            val registerBtn = createRefFor("registerBtn")

            constrain(appName) {
                linkTo(top = parent.top, bottom = parent.bottom, bias = 0.2F)
                linkTo(start = parent.start, end = parent.end)
            }
            constrain(username) {
                top.linkTo(appName.bottom, margin = 16.dp)
                start.linkTo(appName.start)
                end.linkTo(appName.end)
            }
            constrain(email) {
                top.linkTo(username.bottom, margin = 8.dp)
                start.linkTo(username.start)
                end.linkTo(username.end)
            }
            constrain(password) {
                top.linkTo(email.bottom, margin = 8.dp)
                start.linkTo(email.start)
                end.linkTo(email.end)
            }
            constrain(loginBtn) {
                top.linkTo(registerBtn.top)
                bottom.linkTo(registerBtn.bottom)
                start.linkTo(password.start)
            }
            constrain(registerBtn) {
                top.linkTo(password.bottom, margin = 8.dp)
                end.linkTo(password.end)
            }
        }

        ConstraintLayout(
            constraints,
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = "Catetin",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("appName")
            )
            TextFieldView(
                id = "username",
                placeholder = "username",
                icon = Icons.Filled.Person
            )
            TextFieldView(
                id = "email",
                placeholder = "email",
                icon = Icons.Filled.Email
            )
            TextFieldView(
                id = "password",
                placeholder = "password",
                icon = Icons.Filled.Lock
            )

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.layoutId("loginBtn")
            ) {
                Text(
                    text = "Login",
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { navController.navigate(Screen.LoginScreen.route){
                    popUpTo(Screen.LoginScreen.route){
                        inclusive = true
                    }
                } },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.layoutId("registerBtn")
            ) {
                Text(text = "Register", fontFamily = FontFamily.Serif, fontSize = 16.sp)
            }
        }

    }
}