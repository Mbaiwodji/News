package com.instantsystem.news

import com.instantsystem.news.data.ArticleRepositoryImpl
import com.instantsystem.news.mock.MockDataSource
import com.instantsystem.news.mock.MockNewsApiService
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals


class ArticleRepositoryImplTest {
    @Test
    fun articleRepositoryImpl_getArticles_verifyArticleList() = runTest {
        val repository = ArticleRepositoryImpl(MockNewsApiService())

        // Collect the flow emitted by the repository
        val articlesFlow = repository.getArticles().toList()

        // S'assurer qu'une seule émission se produit
        assertEquals(1, articlesFlow.size)
        assertEquals(MockDataSource.articles, articlesFlow.first())
    }

}