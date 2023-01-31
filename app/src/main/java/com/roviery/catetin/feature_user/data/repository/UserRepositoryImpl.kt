package com.roviery.catetin.feature_user.data.repository

import com.roviery.catetin.feature_user.data.data_source.UserDao
import com.roviery.catetin.feature_user.domain.model.User
import com.roviery.catetin.feature_user.domain.repository.UserRepository

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    override suspend fun getUserById(id: Int): User? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

}