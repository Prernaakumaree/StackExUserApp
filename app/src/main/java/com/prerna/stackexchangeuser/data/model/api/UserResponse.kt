package com.josycom.mayorjay.flowoverstack.network

import com.google.gson.annotations.SerializedName
import com.prerna.stackexchangeuser.data.model.api.User

class UserResponse {
    @SerializedName("has_more")
    var hasMore: Boolean? = null

    @SerializedName("items")
    var items: List<User> = emptyList()

    @SerializedName("quota_max")
    var quotaMax: Int? = null

    @SerializedName("quota_remaining")
    var quotaRemaining: Int? = null
}