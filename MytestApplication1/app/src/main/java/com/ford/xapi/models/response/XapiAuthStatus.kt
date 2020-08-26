package com.ford.xapi.models.response

import com.google.gson.annotations.SerializedName

enum class XapiAuthStatus {
    @SerializedName("NotAuthorized")
    UNAUTHORIZED,

    @SerializedName("PrimaryPending")
    PRIMARY_AUTH_PENDING,

    @SerializedName("SecondaryPending")
    SECONDARY_AUTH_PENDING,

    @SerializedName("ResetPending")
    RESET_PENDING,

    @SerializedName("Authorized")
    AUTHORIZED,

    @SerializedName("None")
    NONE
}