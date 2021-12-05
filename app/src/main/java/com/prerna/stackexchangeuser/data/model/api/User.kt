package com.prerna.stackexchangeuser.data.model.api

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("badge_counts")
    val badgeCounts: BadgeCounts,
    @SerializedName("creation_date")
    val creationDate: Int,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("is_employee")
    val isEmployee: Boolean,
    @SerializedName("last_access_date")
    val lastAccessDate: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("reputation")
    val reputation: Int,
    @SerializedName("reputation_change_day")
    val reputationChangeDay: Int,
    @SerializedName("reputation_change_month")
    val reputationChangeMonth: Int,
    @SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Int,
    @SerializedName("reputation_change_week")
    val reputationChangeWeek: Int,
    @SerializedName("reputation_change_year")
    val reputationChangeYear: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("website_url")
    val websiteUrl: String
)