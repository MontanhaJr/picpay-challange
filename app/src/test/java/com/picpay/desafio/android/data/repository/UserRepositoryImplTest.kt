package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.network.PicPayService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private lateinit var service: PicPayService
    private lateinit var repository: UserRepositoryImpl

    @Before
    fun setup() {
        service = mock(PicPayService::class.java)
        repository = UserRepositoryImpl(service)
    }

    @Test
    fun `should return data from service`() = runTest {
        val expected = listOf(UserDto("img", "name", 1, "user"))
        `when`(service.getUsers()).thenReturn(expected)

        val result = repository.getUsers()

        assertEquals(expected, result)
    }
}
