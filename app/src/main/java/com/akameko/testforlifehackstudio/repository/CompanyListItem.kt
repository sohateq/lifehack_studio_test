package com.akameko.testforlifehackstudio.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CompanyListItem(@field:Expose @field:SerializedName("id") val id: Int, @field:Expose @field:SerializedName("name") val name: String, @field:Expose @field:SerializedName("img") val img: String) : Serializable {

    override fun toString(): String {
        return "CompanyListItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}'
    }

}