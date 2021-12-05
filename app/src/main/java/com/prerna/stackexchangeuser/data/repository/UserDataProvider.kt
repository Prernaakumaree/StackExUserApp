package com.prerna.stackexchangeuser.data.repository

import com.josycom.mayorjay.flowoverstack.network.UserResponse
import io.reactivex.Single

interface UserDataProvider {
    fun getUsers(): Single<UserResponse>
}