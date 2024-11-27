package com.instantsystem.news.ui

import com.instantsystem.news.data.ArticleRepository
import com.instantsystem.news.data.ArticleRepositoryImpl
import com.instantsystem.news.data.NewsAPIClient
import com.instantsystem.news.ui.screens.ArticlesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val newsFeatureModule = module {
    single { NewsAPIClient(context = get()) }
    single { get<NewsAPIClient>().apiService }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    viewModel { ArticlesViewModel(get()) }
}