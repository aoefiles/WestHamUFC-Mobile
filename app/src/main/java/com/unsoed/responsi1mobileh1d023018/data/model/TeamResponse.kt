package com.responsi.h1d023018.data.model

import com.google.gson.annotations.SerializedName


data class TeamResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("shortName")
    val shortName: String?,
    @SerializedName("venue")
    val venue: String?,
    @SerializedName("coach")
    val coach: Coach?,
    @SerializedName("squad")
    val squad: List<Player>?
)


data class Coach(
    @SerializedName("name")
    val name: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("nationality")
    val nationality: String?
)

data class Player(
    @SerializedName("name")
    val name: String?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("nationality")
    val nationality: String?
)

