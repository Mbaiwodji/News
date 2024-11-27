package com.instantsystem.news.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.instantsystem.news.R
import com.instantsystem.news.data.model.Article
import com.instantsystem.news.ui.theme.NewsTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    articlesUiState: ArticleUiState,
    retryAction: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    navigateToDetail: (Article) -> Unit,
    closeDetailScreen: () -> Unit = {},
) {
    when (articlesUiState) {
        is ArticleUiState.Loading -> LoadingScreen(modifier = modifier)
        is ArticleUiState.Success -> {
            if (articlesUiState.openedArticle != null && articlesUiState.isDetailOpen) {
                BackHandler {
                    closeDetailScreen()
                }
                DetailScreen(
                    article = articlesUiState.openedArticle
                ) {
                    closeDetailScreen()
                }
            } else {
                ArticlesGridScreen(
                    modifier = modifier.fillMaxWidth(),
                    articles = articlesUiState.articles,
                    contentPadding = contentPadding,
                    navigateToDetail = navigateToDetail
                )
            }
        }

        is ArticleUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier)
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ArticlesGridScreen(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    navigateToDetail: (Article) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(250.dp),
        modifier = modifier
            .padding(horizontal = 4.dp)
            .testTag("newsList"),
        contentPadding = contentPadding
    ) {
        item {
            if (articles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 34.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_data_available),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
        items(articles) { article ->
            ArticleCard(
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                article,
                navigateToDetail = navigateToDetail
            )
        }
    }
}


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    navigateToDetail: (Article) -> Unit
) {
    Card(
        modifier = modifier
            .height(400.dp)
            .clickable {
                navigateToDetail(article)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.article_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = article.title,
                style = MaterialTheme.typography.labelMedium
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    NewsTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    NewsTheme {
        ErrorScreen({})
    }
}