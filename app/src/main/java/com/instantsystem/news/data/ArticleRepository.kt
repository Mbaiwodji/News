package com.instantsystem.news.data

import com.instantsystem.news.data.model.Article
import com.instantsystem.news.data.service.NewsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Interface pour la répository des articles
 */
interface ArticleRepository {
    suspend fun getArticles(): Flow<List<Article>>
}

/**
 * Implémentation de l'interface [ArticleRepository]
 * @param newsApiService [NewsApiService]
 * @return [ArticleRepository]
 */
class ArticleRepositoryImpl(private val newsApiService: NewsApiService) : ArticleRepository {
    override suspend fun getArticles(): Flow<List<Article>> = flow {
        emit(newsApiService.getArticles().articles)
    }
}