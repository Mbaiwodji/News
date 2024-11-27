package com.instantsystem.news.data.model

import kotlinx.serialization.Serializable

/**
 *  Classe de données représentant un article d'actualité.
 *  @param author L'auteur de l'article.
 *  @param title Le titre de l'article.
 *  @param description La description de l'article.
 *  @param url L'URL de l'article.
 *  @param urlToImage L'URL de l'image de l'article.
 *  @param publishedAt La date de publication de l'article.
 *  @param content Le contenu de l'article.
 */
@Serializable
data class Article(
    val author: String? = null,
    val title: String = "",
    val description: String? = null,
    val url: String = "",
    val urlToImage: String? = null,
    val publishedAt: String = "",
    val content: String? = null
)

/**
 * Classe de données pour la réponse de l'API
 * @param status L'état de la réponse
 * @param totalResults Le nombre total d'articles
 * @param articles La liste des articles
 */
@Serializable
data class NewsApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)