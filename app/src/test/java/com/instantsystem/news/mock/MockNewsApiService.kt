package com.instantsystem.news.mock

import com.instantsystem.news.data.model.NewsApiResponse
import com.instantsystem.news.data.service.NewsApiService

class MockNewsApiService : NewsApiService {
    override suspend fun getArticles(): NewsApiResponse {
        return MockDataSource.response
    }
}