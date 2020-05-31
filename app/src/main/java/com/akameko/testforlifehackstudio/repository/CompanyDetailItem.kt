package com.akameko.testforlifehackstudio.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CompanyDetailItem(@field:Expose @field:SerializedName("id") val id: Int, @field:Expose @field:SerializedName("name") val name: String, @field:Expose @field:SerializedName("img") val img: String, @field:Expose @field:SerializedName("description") val description: String, @field:Expose @field:SerializedName("lat") val lat: String, @field:Expose @field:SerializedName("lon") val lon: String, @field:Expose @field:SerializedName("www") val www: String, @field:Expose @field:SerializedName("phone") val phone: String) {

    override fun toString(): String {
        return "CompanyDetailItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", www='" + www + '\'' +
                ", phone='" + phone + '\'' +
                '}'
    }

}