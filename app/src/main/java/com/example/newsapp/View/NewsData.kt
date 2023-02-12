package com.example.newsapp.View

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.Utils.ApiState
import com.example.newsapp.ViewModel.NewsViewModel
import com.example.newsapp.Model.Source

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsData(onNavigateToDetailScreen : (String)->Unit ,
             viewModel: NewsViewModel) {
    val newsCategory = listOf("business","entertainment","general","health","science","sports","technology")
    var currentNewsCategoryState by remember { mutableStateOf("business") }
    var scrollableState = rememberScrollState()

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            NewsHeader()
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(scrollableState)
                ) {
                    newsCategory.forEach {
                        NewsCategory(
                            title = it,
                            isSelected = it == currentNewsCategoryState,
                            onValueChange = {
                                currentNewsCategoryState = it
                                viewModel.getNews(selectedCategory = it)
                            })
                    }
                }
            }

            GetNewsDataForEachRow(newsViewModel = viewModel, onNavigateToDetailScreen = onNavigateToDetailScreen)
        }
    }
}


@Composable
fun GetNewsDataForEachRow(newsViewModel: NewsViewModel,
                          onNavigateToDetailScreen : (String)->Unit) {
    when(val result = newsViewModel.responseData.value) {
        is ApiState.Success -> {
            LazyColumn() {
                items(count = result.newsData.count()) {index: Int ->
                    EachRow(newsSource = result.newsData[index], onNavigateToDetailScreen = onNavigateToDetailScreen)
                }
            }
        }

        is ApiState.Failure -> {
            result.throwable.localizedMessage?.let { Text(text = it) }
        }

        is ApiState.Loading -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                CircularProgressIndicator()
            }
        }

        is ApiState.Empty -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EachRow(
    newsSource: Source,
    onNavigateToDetailScreen: (String)->Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        onClick = {  onNavigateToDetailScreen(newsSource.url) }
    ) {

        Text(
            text = newsSource.name,
            modifier = Modifier.padding(10.dp),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Text(
            text = newsSource.description,
            modifier = Modifier.padding(10.dp),
            fontStyle = FontStyle.Italic
        )

        Text(
            text = "Country: " + newsSource.country.uppercase(),
            modifier = Modifier.padding(10.dp),
            fontStyle = FontStyle.Italic
        )
    }
}