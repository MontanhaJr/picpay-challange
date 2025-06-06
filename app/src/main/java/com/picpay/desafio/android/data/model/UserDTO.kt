package com.picpay.desafio.android.data.model

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.User

data class UserDto(
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("username") val username: String?
)

fun UserDto.toDomain(): User? {
    if (img.isNullOrBlank() || name.isNullOrBlank() || username.isNullOrBlank() || id == null) {
        return null
    }
    return User(img, name, id, username)
}

fun UserDto.toEntity(): UserEntity? {
    if (img.isNullOrBlank() || name.isNullOrBlank() || username.isNullOrBlank() || id == null) {
        return null
    }
    return UserEntity(id, name, username, img)
}

