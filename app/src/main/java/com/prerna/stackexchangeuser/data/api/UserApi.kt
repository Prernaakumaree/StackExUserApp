package com.prerna.stackexchangeuser.data.api

import com.prerna.stackexchangeuser.data.repository.UserDataProvider

class UserApi(private val userService: UserService) : UserDataProvider {

    override fun getUsers() = userService.getAllUsers()
}