package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.model.UserDto

class UserRepositoryImpl(private val service: PicPayService) : UserRepository {
    override suspend fun getUsers(): List<UserDto> {
        return service.getUsers()
    }
}
