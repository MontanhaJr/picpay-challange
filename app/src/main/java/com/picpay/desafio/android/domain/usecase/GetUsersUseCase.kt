package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.data.model.toDomain

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(): List<User> = repository.getUsers().map { it.toDomain() }
}
