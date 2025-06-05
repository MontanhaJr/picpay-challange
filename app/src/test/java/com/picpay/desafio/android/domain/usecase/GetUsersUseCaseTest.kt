package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setup() {
        repository = mock(UserRepository::class.java)
        useCase = GetUsersUseCase(repository)
    }

    @Test
    fun `should return only valid users`() = runTest {
        val dtos = listOf(
            UserDto("img1", "name1", 1, "username1"),
            UserDto(null, "name2", 2, "username2"),
            UserDto("img3", "name3", 3, "username3")
        )
        `when`(repository.getUsers()).thenReturn(dtos)

        val result = useCase()

        assertEquals(2, result.size)
        assertTrue(result.all { it.img.isNotEmpty() })
    }
}
