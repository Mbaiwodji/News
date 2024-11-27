package com.instantsystem.news.data.service

import com.instantsystem.news.data.model.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface pour la communication avec l'API
 * @return [NewsApiResponse]
 */
interface NewsApiService {
    @GET("top-headlines")
    suspend fun getArticles(): NewsApiResponse
}