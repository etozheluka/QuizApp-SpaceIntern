package com.space.quizapp.di

import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.domain.model.QuizDomainModelItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module

val quizModule = module {
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        val moshi: Moshi = get()
        val listType = Types.newParameterizedType(List::class.java, QuizDtoItem::class.java)
        moshi.adapter<List<QuizDtoItem>>(listType)
    }
}
