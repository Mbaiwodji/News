package com.instantsystem.news.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.instantsystem.news.R
import com.instantsystem.news.ui.screens.ArticleUiState
import com.instantsystem.news.ui.screens.ArticlesViewModel
import com.instantsystem.news.ui.screens.HomeScreen
import org.koin.androidx.compose.getViewModel


@Composable
fun NewsApp() {

    val articlesViewModel: ArticlesViewModel = getViewModel()
    val articlesUiState by articlesViewModel.articleUiState.collectAsState()
    Scaffold(
        topBar = {
            if (articlesUiState is ArticleUiState.Success &&
                !(articlesUiState as ArticleUiState.Success).isDetailOpen
            ) {
                NewsTopAppBar(modifier = Modifier.fillMaxWidth())
            }

        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            HomeScreen(
                articlesUiState = articlesUiState,
                retryAction = articlesViewModel::getArticles,
                contentPadding = it,
                navigateToDetail = {
                    articlesViewModel.openDetail(it)
                },
                closeDetailScreen = {
                    articlesViewModel.closeDetailScreen()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        title = {
            Text(stringResource(R.string.news_app))
        }
    )
}
