package com.picpay.desafio.android.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.view.state.ContactsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ContactsUiState>(ContactsUiState.Loading)
    val state: StateFlow<ContactsUiState> = _state

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = ContactsUiState.Loading
            try {
                val users = getUsersUseCase()
                _state.value = ContactsUiState.Success(users)
            } catch (e: Exception) {
                _state.value = ContactsUiState.Error("Erro ao carregar usuários")
            }
        }
    }
}
