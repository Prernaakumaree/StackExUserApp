package com.prerna.stackexchangeuser.data.api

import com.josycom.mayorjay.flowoverstack.network.UserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {

    @GET("/2.3/users")
    //order=desc&max=10&sort=name&site=stackoverflow
    fun getAllUsers(
        @Query("order") order: String = "asc",
        @Query("max") max: Int = 30,
        @Query("sort") sortCondition: String = "name",
        @Query("site") site: String = "stackoverflow",
        @Query("key") siteKey: String = "oLbbqCsuMIxE2HEHhtfKvQ(("
    ): Single<UserResponse>

    companion object {
        fun createService(retrofit: Retrofit) = retrofit.create(UserService::class.java)
    }
}
