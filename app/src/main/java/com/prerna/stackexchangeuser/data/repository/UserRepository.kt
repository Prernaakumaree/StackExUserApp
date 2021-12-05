package com.prerna.stackexchangeuser.data.repository

import com.josycom.mayorjay.flowoverstack.network.UserResponse
import com.prerna.stackexchangeuser.data.api.UserApi
import io.reactivex.Single
import org.koin.core.KoinComponent

class UserRepository(
    private val userApi: UserApi
) : KoinComponent, UserDataProvider {

    override fun getUsers(): Single<UserResponse> {
        return userApi.getUsers()
    }

}