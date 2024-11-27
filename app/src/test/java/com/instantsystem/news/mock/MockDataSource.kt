package com.instantsystem.news.mock

import com.instantsystem.news.data.model.Article
import com.instantsystem.news.data.model.NewsApiResponse

object MockDataSource {
    private const val status = "ok"
    private const val totalResults = 2
    val articles = listOf(
        Article(
            author = "Kelly Kasulis Cho",
            title = "Bird flu detected in raw milk sold at California store - The Washington Post",
            description = "The H5N1 virus was found in a sample of unpasteurized milk...",
            url = "https://www.washingtonpost.com/health/2024/11/25/bird-flu-virus-h5n1-milk/",
            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?...",
            publishedAt = "2024-11-25T10:42:26Z",
            content = "Bird flu, a virus that can also affect humans, has been discovered..."
        ),
        Article(
            author = null,
            title = "Matt Gaetz joins Cameo – and is charging people hundreds for pep talks - Sky News",
            description = "",
            url = "https://news.sky.com/story/matt-gaetz-joins-cameo...",
            urlToImage = "https://e3.365dm.com/24/11/1600x900/skynews-matt-gaetz...",
            publishedAt = "2024-11-25T10:08:26Z",
            content = "Matt Gaetz has joined the celebrity video platform Cameo..."
        )
    )
    val response = NewsApiResponse(status, totalResults, articles)
}