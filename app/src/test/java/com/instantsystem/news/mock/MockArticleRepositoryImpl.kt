package com.instantsystem.news.mock

import com.instantsystem.news.data.ArticleRepository
import com.instantsystem.news.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockArticleRepositoryImpl: ArticleRepository {
    override suspend fun getArticles(): Flow<List<Article>> {
        return flow {
            emit(MockDataSource.response.articles)
        }
    }

}