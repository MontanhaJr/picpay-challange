package com.picpay.desafio.android.view.viewmodel

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.view.state.ContactsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ContactsViewModelTest {

    private lateinit var useCase: GetUsersUseCase
    private lateinit var viewModel: ContactsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = mock(GetUsersUseCase::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should have Loading state initially`() = runTest {
        `when`(useCase.invoke()).thenReturn(emptyList())

        viewModel = ContactsViewModel(useCase)

        val initialState = viewModel.state.value
        assertTrue(initialState is ContactsUiState.Loading)
    }

    @Test
    fun `should emit Success state`() = runTest {
        val users = listOf(User("img", "name", 1, "username"))
        `when`(useCase.invoke()).thenReturn(users)

        viewModel = ContactsViewModel(useCase)

        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is ContactsUiState.Success)
        assertEquals(users, (state as ContactsUiState.Success).users)
    }

    @Test
    fun `should emit Error state on exception`() = runTest(testDispatcher) {
        `when`(useCase.invoke()).thenThrow(RuntimeException("Erro"))

        viewModel = ContactsViewModel(useCase)

        val state = viewModel.state.first { it is ContactsUiState.Error }

        assertTrue(state is ContactsUiState.Error)
    }

    @Test
    fun `should emit Success state with empty list`() = runTest(testDispatcher) {
        val users = emptyList<User>()
        `when`(useCase.invoke()).thenReturn(users)

        viewModel = ContactsViewModel(useCase)

        val state = viewModel.state.first { it is ContactsUiState.Success }

        assertTrue(state is ContactsUiState.Success)
        assertEquals(users, (state as ContactsUiState.Success).users)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should transition from Loading to Success`() = runTest {
        val users = listOf(User("img", "name", 1, "username"))
        `when`(useCase.invoke()).thenReturn(users)

        viewModel = ContactsViewModel(useCase)

        val initialState = viewModel.state.value
        assertTrue(initialState is ContactsUiState.Loading)

        advanceUntilIdle()

        val finalState = viewModel.state.value
        assertTrue(finalState is ContactsUiState.Success)
        assertEquals(users, (finalState as ContactsUiState.Success).users)
    }


}
