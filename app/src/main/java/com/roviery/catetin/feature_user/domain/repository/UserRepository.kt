package com.roviery.catetin.feature_user.domain.repository

import com.roviery.catetin.feature_user.domain.model.User

interface UserRepository {

    suspend fun getUserById(id: Int): User?
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
    
}