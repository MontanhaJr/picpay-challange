package com.picpay.desafio.android

import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.view.viewmodel.ContactsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    single<UserRepository> { FakeUserRepository() }
    single { GetUsersUseCase(get()) }
    viewModel { ContactsViewModel(get()) }
}
