package com.roviery.catetin.feature_user.domain.use_case

import com.roviery.catetin.feature_user.domain.model.InvalidUserException
import com.roviery.catetin.feature_user.domain.model.User
import com.roviery.catetin.feature_user.domain.repository.UserRepository

class AddUser(
    private val repository: UserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if (user.fullname.isBlank()) {
            throw InvalidUserException("Fullname can't be empty")
        }
        if (user.email.isBlank()) {
            throw InvalidUserException("Email can't be empty")
        }
        if (user.password.isBlank()) {
            throw InvalidUserException("Password can't be empty")
        }
        repository.insertUser(user)
    }
}