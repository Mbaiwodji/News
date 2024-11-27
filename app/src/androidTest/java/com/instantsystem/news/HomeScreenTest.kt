package com.instantsystem.news

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.instantsystem.news.data.model.Article
import com.instantsystem.news.ui.screens.ArticleUiState
import com.instantsystem.news.ui.screens.ErrorScreen
import com.instantsystem.news.ui.screens.HomeScreen
import com.instantsystem.news.ui.screens.LoadingScreen

import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class HomeScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun testHomeScreen_LoadingScreen() {
        rule.setContent { LoadingScreen() }
        rule.onNodeWithContentDescription("Loading").assertExists()
    }

    @Test
    fun testHomeScreen_ErrorScreen() {
        rule.setContent { ErrorScreen(
            retryAction = {}
        ) }
        rule.onNodeWithText("Failed to load")
        rule.onNodeWithText("Retry").performClick()
    }

    @Test
    fun testHomeScreen_DisplaysArticles() {
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
        rule.setContent {
            HomeScreen(
                articlesUiState = ArticleUiState.Success(articles),
                retryAction = {},
                navigateToDetail = {}
            )
        }

        rule.onNodeWithTag("newsList").assertIsDisplayed()
    }
}