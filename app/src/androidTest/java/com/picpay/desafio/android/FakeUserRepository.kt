package com.picpay.desafio.android

import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.repository.UserRepository


class FakeUserRepository : UserRepository {
    override suspend fun getUsers(): List<UserDto> {
        return listOf(
            UserDto(
                id = 1,
                name = "João",
                username = "joaoTeste",
                img = "https://teste/1.jpg"
            ),
            UserDto(
                id = 2,
                name = "Maria",
                username = "mariaTeste",
                img = "https://teste/1.jpg"
            )
        )
    }
}