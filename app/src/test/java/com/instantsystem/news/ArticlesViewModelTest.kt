package com.instantsystem.news

import com.instantsystem.news.mock.MockArticleRepositoryImpl
import com.instantsystem.news.mock.MockDataSource
import com.instantsystem.news.rules.TestDispatcherRule
import com.instantsystem.news.ui.screens.ArticleUiState
import com.instantsystem.news.ui.screens.ArticlesViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class ArticlesViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun articlesViewModel_getArticles() = runTest {
        // Mock du repository
        val mockRepository = MockArticleRepositoryImpl()

        // Création du ViewModel
        val articlesViewModel = ArticlesViewModel(articleRepository = mockRepository)

        // Collecte de l'état émis par le StateFlow
        val actualState = articlesViewModel.articleUiState.first()

        // Vérification du contenu de l'état Success
        assertEquals(
            ArticleUiState.Success(MockDataSource.articles), // Attendu
            actualState // Réel
        )
    }
}