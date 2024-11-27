package com.instantsystem.news.data

import android.content.Context
import com.instantsystem.news.R
import com.instantsystem.news.data.service.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.util.Locale

/**
 * @param context contexte de l'application
 */
class NewsAPIClient(private val context: Context) {
    private val baseUrl = "https://newsapi.org/v2/"

    /**
     * Création du client OkHttp avec un intercepteur qui ajoute la clé d'API
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val url = original.url.newBuilder()
                .addQueryParameter("apiKey", context.getString(R.string.news_api_key))
                .addQueryParameter("language", Locale.getDefault().language) // Get phone's language
                .build()
            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }
        .build()

    /**
     * Création du format JSON
     */
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    /**
     * Création du client Retrofit
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .build()

    /**
     * Création de l'API
     */
    val apiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}