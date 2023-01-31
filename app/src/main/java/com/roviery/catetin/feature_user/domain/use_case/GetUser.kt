package com.roviery.catetin.feature_user.domain.use_case

import com.roviery.catetin.feature_user.domain.model.User
import com.roviery.catetin.feature_user.domain.repository.UserRepository

class GetUser(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: Int): User? {
        return repository.getUserById(id)
    }
}