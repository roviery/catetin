package com.roviery.catetin.feature_user.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int? = null,
    val fullname: String,
    val email: String,
    val password: String,
    val createdAt: Long,
)

class InvalidUserException(message: String) : Exception(message)
