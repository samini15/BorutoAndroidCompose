package com.example.borutoandroidcompose.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.presentation.widgets.InfoBox
import com.example.borutoandroidcompose.presentation.widgets.OrderedList
import com.example.borutoandroidcompose.ui.theme.INFO_BOX_ICON_SIZE
import com.example.borutoandroidcompose.ui.theme.LARGER_PADDING
import com.example.borutoandroidcompose.ui.theme.MEDIUM_PADDING
import com.example.borutoandroidcompose.ui.theme.MIN_BOTTOM_SHEET_HEIGHT
import com.example.borutoandroidcompose.ui.theme.titleColor
import com.example.borutoandroidcompose.utils.Constants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_BOTTOM_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { hero ->
                DetailsBottomSheetContent(selectedHero = hero)
            }
        }
    ) { innerPadding ->
        selectedHero?.let { hero ->
            BackgroundImage(
                heroImage = hero.image,
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun DetailsBottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.titleColor
) {
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(LARGER_PADDING)
    ) {

        // Title section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGER_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(INFO_BOX_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = infoBoxIconColor
            )
            Text(
                modifier = Modifier.weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

        // Info boxes
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                descriptionText = "Power",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.birthMonth,
                descriptionText = "Month",
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.birthDay,
                descriptionText = "Birthday",
                textColor = contentColor
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = contentColor,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier
                .alpha(alpha = ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = contentColor,
            maxLines = Constants.DESCRIPTION_TEXT_MAX_LINES
        )

        // Ordered Lists
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderedList(title = stringResource(R.string.family), items = selectedHero.family, textColor = contentColor)
            OrderedList(title = stringResource(R.string.abilities), items = selectedHero.abilities, textColor = contentColor)
            OrderedList(title = stringResource(R.string.nature_types), items = selectedHero.natureTypes, textColor = contentColor)
        }
    }
}

@Composable
fun BackgroundImage(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseClicked: () -> Unit
) {
    val imageUrl = "${Constants.BORUTO_API_BASE_URL}$heroImage"
    val painter = rememberAsyncImagePainter(model = imageUrl)
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image_content_description),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(modifier = Modifier.padding(MEDIUM_PADDING), onClick = { onCloseClicked() }) {
                Icon(
                    modifier = Modifier.size(INFO_BOX_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon_content_description),
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun DetailsBottomSheetContentPreview() {
    DetailsBottomSheetContent(selectedHero =
        Hero(
            id = 2,
            name = "Sasuke",
            image = "/images/sasuke.jpg",
            about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
            rating = 3.4,
            power = 77,
            birthMonth = "July",
            birthDay = "21st",
            family = listOf(
                "Fugaku",
                "Mikoto",
                "Itachi",
                "Sarada",
                "Sakura"
            ),
            abilities = listOf(
                "Sharingan",
                "Rinnegan",
                "Sussano",
                "Amateratsu",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Lightning",
                "Fire",
                "Wind",
                "Earth",
                "Water"
            )
        )
    )
}