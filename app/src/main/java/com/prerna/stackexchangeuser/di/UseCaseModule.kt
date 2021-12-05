package com.prerna.stackexchangeuser.di

import com.prerna.stackexchangeuser.ui.usecase.UserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { UserUseCase(get()) }

}