package com.prerna.stackexchangeuser.ui.user

import com.prerna.stackexchangeuser.data.model.api.User

sealed class UserScreenState {
    object Loading : UserScreenState()
    class DataAvailable(val users: List<User>) : UserScreenState()
    class Error(val error: Throwable) : UserScreenState()
    object FinishedLoading : UserScreenState()
}