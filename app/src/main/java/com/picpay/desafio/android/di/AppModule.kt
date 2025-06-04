package com.picpay.desafio.android.di

import com.picpay.desafio.android.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.view.viewmodels.ContactsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicPayService::class.java)
    }

    single<UserRepository> { UserRepositoryImpl(get()) }
    single { GetUsersUseCase(get()) }
    viewModel { ContactsViewModel(get()) }
}