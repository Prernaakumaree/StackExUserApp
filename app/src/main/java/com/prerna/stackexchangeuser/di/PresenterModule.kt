package com.prerna.stackexchangeuser.di

import com.prerna.stackexchangeuser.ui.detail.UserDetailPresenter
import com.prerna.stackexchangeuser.ui.user.UserPresenter
import org.koin.dsl.module

val presenterModule = module {

    factory { UserPresenter(get()) }

    factory { UserDetailPresenter(get()) }
}