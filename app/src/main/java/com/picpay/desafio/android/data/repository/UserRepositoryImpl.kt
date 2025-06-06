package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.UserDao
import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.model.toDto
import com.picpay.desafio.android.data.model.toEntity

class UserRepositoryImpl(
    private val service: PicPayService,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUsers(): List<UserDto> {
        return try {
            val usersFromApi = service.getUsers()
                .mapNotNull { it.toEntity() }
                .also {
                    userDao.clearUsers()
                    userDao.insertUsers(it)
                }
                .map { it.toDto() }
            usersFromApi
        } catch (e: Exception) {
            userDao.getUsers().map { it.toDto() }
        }
    }
}
