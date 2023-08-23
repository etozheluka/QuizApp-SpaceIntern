package com.space.quizapp.di

import com.space.quizapp.data.remote.service.QuizApiService
import com.space.quizapp.data.remote.service.QuizApiService.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val quizModule = module {
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(get())
            )
            .build()
    }
    single {
        provideService(get())
    }

}

fun provideService(retrofit: Retrofit): QuizApiService =
    retrofit.create(QuizApiService::class.java)
