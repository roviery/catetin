package com.roviery.catetin.feature_user.domain.use_case

data class UserUseCases(
    val getUser: GetUser,
    val addUser: AddUser,
    val deleteUser: DeleteUser,
)