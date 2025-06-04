package com.picpay.desafio.android.view

import com.picpay.desafio.android.domain.model.User


sealed class ContactsUiState {
    object Loading : ContactsUiState()
    data class Success(val users: List<User>) : ContactsUiState()
    data class Error(val message: String) : ContactsUiState()
}
