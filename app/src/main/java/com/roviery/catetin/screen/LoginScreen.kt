@file:OptIn(ExperimentalMaterial3Api::class)

package com.roviery.catetin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import com.roviery.catetin.ui.theme.Black
import com.roviery.catetin.ui.theme.CatetinTheme
import com.roviery.catetin.ui.theme.White

@Composable
fun LoginScreen(navController: NavController) {
    CatetinTheme {
        val constraints = ConstraintSet {
            val appName = createRefFor("appName")
            val username = createRefFor("username")
            val password = createRefFor("password")
            val registerBtn = createRefFor("registerBtn")
            val loginBtn = createRefFor("loginBtn")

            constrain(appName) {
                linkTo(top = parent.top, bottom = parent.bottom, bias = 0.2F)
                linkTo(start = parent.start, end = parent.end)
            }
            constrain(username) {
                top.linkTo(appName.bottom, margin = 16.dp)
                start.linkTo(appName.start)
                end.linkTo(appName.end)
            }
            constrain(password) {
                top.linkTo(username.bottom, margin = 8.dp)
                start.linkTo(username.start)
                end.linkTo(username.end)
            }
            constrain(registerBtn) {
                top.linkTo(loginBtn.top)
                bottom.linkTo(loginBtn.bottom)
                start.linkTo(password.start)
            }
            constrain(loginBtn) {
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
                id = "password",
                placeholder = "password",
                icon = Icons.Filled.Lock
            )

            TextButton(
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                modifier = Modifier.layoutId("registerBtn")
            ) {
                Text(
                    text = "Register",
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.layoutId("loginBtn")
            ) {
                Text(text = "Login", fontFamily = FontFamily.Serif, fontSize = 16.sp)
            }
        }

    }
}

@Composable
fun TextFieldView(
    id: String,
    placeholder: String,
    icon: ImageVector,
) {
    var textState by remember {
        mutableStateOf("")
    }

    TextField(
        value = textState,
        onValueChange = { textState = it },
        placeholder = { Text(text = placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLeadingIconColor = Black,
            containerColor = White
        ),
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = placeholder)
        },
        visualTransformation =
        if (placeholder == "password") PasswordVisualTransformation()
        else VisualTransformation.None,
        modifier = Modifier
            .layoutId(id)
            .background(White)
    )
}