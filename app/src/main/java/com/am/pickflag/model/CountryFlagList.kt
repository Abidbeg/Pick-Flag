package com.am.pickflag.model


import com.google.gson.annotations.SerializedName

data class CountryFlagList(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("msg")
    val msg: String
)

data class Data(
    @SerializedName("flag")
    val flag: String,
    @SerializedName("iso2")
    val iso2: String,
    @SerializedName("iso3")
    val iso3: String,
    @SerializedName("name")
    val name: String
)