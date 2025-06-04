package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.UserDto

interface UserRepository {
    suspend fun getUsers(): List<UserDto>
}

