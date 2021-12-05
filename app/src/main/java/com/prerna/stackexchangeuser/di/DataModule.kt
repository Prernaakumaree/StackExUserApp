package com.prerna.stackexchangeuser.di

import com.prerna.stackexchangeuser.data.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single { UserRepository(get()) }

}