package com.example.user.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

data class UserObject(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<UserInfo>?
)

data class Info(
    @SerializedName("page")
    val page: Int?
)

@Parcelize
data class UserInfo(
    @SerializedName("dob")
    val dob: Dob?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: Name?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("picture")
    val picture: Picture?
) : Parcelable {
    @Parcelize
    data class Dob(
        @SerializedName("age")
        val age: Int?,
        @SerializedName("date")
        val date: String?
    ) : Parcelable {

        companion object {
            const val ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
            const val FORMAT = "yyyy-MM-dd"

        }

        fun getDob(): String {
            val calendar = Calendar.getInstance()
            calendar.time = SimpleDateFormat(ISO_8601_FORMAT).parse(date)
            val correctFormat = SimpleDateFormat(FORMAT, Locale.getDefault())
            return correctFormat.format(calendar.time)
        }
    }

    @Parcelize
    data class Name(
        @SerializedName("first")
        val first: String?,
        @SerializedName("last")
        val last: String?,
        @SerializedName("title")
        val title: String?
    ) : Parcelable {
        fun getFullName() = "$first $last"
    }

    @Parcelize
    data class Picture(
        @SerializedName("large")
        val large: String?,
        @SerializedName("medium")
        val medium: String?
    ) : Parcelable
}
