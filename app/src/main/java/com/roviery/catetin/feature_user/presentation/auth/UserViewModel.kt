package com.roviery.catetin.feature_user.presentation.auth

import android.provider.ContactsContract
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.roviery.catetin.feature_user.domain.model.User
import com.roviery.catetin.feature_user.domain.use_case.UserUseCases
import com.roviery.catetin.feature_user.presentation.RegisterFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _state = mutableStateOf(RegisterFormState())

    fun register() = userUseCases.addUser
}