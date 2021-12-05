package com.prerna.stackexchangeuser.ui.usecase

import com.josycom.mayorjay.flowoverstack.network.UserResponse
import com.prerna.stackexchangeuser.data.repository.UserRepository
import io.reactivex.Single
import org.koin.core.KoinComponent

class UserUseCase(private val repository: UserRepository) : KoinComponent {

    fun execute(): Single<UserResponse> {
        return repository.getUsers()
    }
}