package com.picpay.desafio.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val img: String
)

fun UserEntity.toDto(): UserDto =
    UserDto(img = img, name = name, id = id, username = username)
