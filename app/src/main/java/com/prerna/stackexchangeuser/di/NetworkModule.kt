package com.prerna.stackexchangeuser.di

import com.prerna.stackexchangeuser.data.api.UserApi
import com.prerna.stackexchangeuser.data.api.UserService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single { UserService.createService(get()) }

    single { UserApi(get()) }

}