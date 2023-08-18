package com.example.borutoandroidcompose.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.navigation.Screen
import com.example.borutoandroidcompose.presentation.widgets.RatingWidget
import com.example.borutoandroidcompose.ui.theme.CARD_CORNER_RADIUS
import com.example.borutoandroidcompose.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoandroidcompose.ui.theme.MEDIUM_PADDING
import com.example.borutoandroidcompose.ui.theme.SMALL_PADDING
import com.example.borutoandroidcompose.ui.theme.topAppBarContentColor
import com.example.borutoandroidcompose.utils.Constants

@Composable
fun ListContent(
    navController: NavHostController,
    items: LazyPagingItems<Hero>,
    topPadding: Dp
) {
    LazyColumn(
        modifier = Modifier.padding(top = topPadding),
        contentPadding = PaddingValues(all = MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(count = items.itemCount) { i ->
            val item = items[i]
            item?.let {  hero ->
                HeroItem(hero = hero, navController = navController)
            }

        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {
    //val painter = rememberAsyncImagePainter(model = "${Constants.BORUTO_API_BASE_URL}${hero.image}")
    val model = ImageRequest.Builder(LocalContext.current)
        .data("${Constants.BORUTO_API_BASE_URL}${hero.image}")
        .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
        .build()

    Box(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
        },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = CARD_CORNER_RADIUS)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = model,
                contentDescription = stringResource(R.string.hero_image_content_description),
                placeholder = painterResource(id = R.drawable.ic_placeholder),
                error = painterResource(id = R.drawable.ic_placeholder),
                contentScale = ContentScale.Crop
            )
        }
        Surface(modifier = Modifier
            .fillMaxHeight(fraction = 0.4f)
            .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = CARD_CORNER_RADIUS,
                bottomEnd = CARD_CORNER_RADIUS
            )
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)) {

                // HERO NAME
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = hero.name,
                    color = MaterialTheme.colorScheme.topAppBarContentColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                // ABOUT
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = hero.about,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )

                // Rating
                Row(modifier = Modifier.padding(top = SMALL_PADDING), verticalAlignment = Alignment.CenterVertically) {
                    RatingWidget(modifier = Modifier.padding(end = MEDIUM_PADDING), rating = hero.rating)
                    Text(text = "(${hero.rating})", textAlign = TextAlign.Center, color = Color.White.copy(alpha = ContentAlpha.medium))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasssuke",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 3.2,
            power = 70,
            birthMonth = "",
            birthDay = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        navController = rememberNavController()
    )
}