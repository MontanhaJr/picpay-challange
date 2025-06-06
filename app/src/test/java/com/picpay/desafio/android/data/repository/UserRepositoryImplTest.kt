package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.model.UserDto
import com.picpay.desafio.android.data.model.UserEntity
import com.picpay.desafio.android.data.model.toEntity
import com.picpay.desafio.android.data.model.toDto
import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.model.UserDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.times

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private lateinit var service: PicPayService
    private lateinit var userDao: UserDao
    private lateinit var repository: UserRepositoryImpl

    private val userDtoList = listOf(UserDto("img", "name", 1, "user"))
    private val userEntityList = listOf(UserEntity(1, "name", "user", "img"))

    @Before
    fun setup() {
        service = mock(PicPayService::class.java)
        userDao = mock(UserDao::class.java)
        repository = UserRepositoryImpl(service, userDao)
    }

    @Test
    fun `should fetch from API, clear and insert cache, then return converted data`() = runTest {
        `when`(service.getUsers()).thenReturn(userDtoList)

        val result = repository.getUsers()

        assertEquals(userDtoList, result)
        verify(userDao).clearUsers()
        verify(userDao).insertUsers(userEntityList)
    }

    @Test
    fun `should return data from local cache if API fails`() = runTest {
        `when`(service.getUsers()).thenThrow(RuntimeException("API failure"))
        `when`(userDao.getUsers()).thenReturn(userEntityList)

        val result = repository.getUsers()

        assertEquals(userEntityList.map { it.toDto() }, result)
        verify(userDao, never()).clearUsers()
        verify(userDao, never()).insertUsers(any())
    }
}
