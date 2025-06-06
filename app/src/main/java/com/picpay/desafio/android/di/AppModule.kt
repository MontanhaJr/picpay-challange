package com.picpay.desafio.android.di

import androidx.room.Room
import com.picpay.desafio.android.data.AppDatabase
import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.view.viewmodel.ContactsViewModel
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

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    single { get<AppDatabase>().userDao() }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single { GetUsersUseCase(get()) }
    viewModel { ContactsViewModel(get()) }
}