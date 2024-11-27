package com.instantsystem.news.ui.screens

import androidx.lifecycle.ViewModel
import com.instantsystem.news.data.ArticleRepository
import com.instantsystem.news.data.model.Article
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * UI state de l'écran Home
 */
sealed interface ArticleUiState {
    data class Success(
        val articles: List<Article>,
        val isDetailOpen: Boolean = false,
        val openedArticle: Article? = null,
    ) : ArticleUiState

    object Error : ArticleUiState
    object Loading : ArticleUiState
}

/**
 * ViewModel contenant la liste des articles
 */
class ArticlesViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articleUiState: MutableStateFlow<ArticleUiState> =
        MutableStateFlow(ArticleUiState.Loading)
    val articleUiState: StateFlow<ArticleUiState> = _articleUiState

    /**
     * Initialisation
     */
    init {
        getArticles();
    }

    /**
     * Récupère la liste des articles à partir du repository
     */
    fun getArticles() {
        viewModelScope.launch {
            articleRepository.getArticles()
                .catch { _articleUiState.value = ArticleUiState.Error }
                .collect { articles ->
                    _articleUiState.value =
                        ArticleUiState.Success(articles.filter { it.urlToImage != null })
                }
        }
    }

    fun openDetail(article: Article) {
        val currentState = _articleUiState.value
        if (currentState is ArticleUiState.Success) {
            _articleUiState.value = currentState.copy(isDetailOpen = true, openedArticle = article)
        }
    }

    fun closeDetailScreen() {
        val currentState = _articleUiState.value
        if (currentState is ArticleUiState.Success) {
            _articleUiState.value = currentState.copy(isDetailOpen = false)
        }
    }
}