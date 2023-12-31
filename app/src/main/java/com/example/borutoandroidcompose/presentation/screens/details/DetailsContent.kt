package com.example.borutoandroidcompose.presentation.screens.details

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.palette.graphics.Target
import coil.compose.rememberAsyncImagePainter
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.presentation.widgets.InfoBox
import com.example.borutoandroidcompose.presentation.widgets.OrderedList
import com.example.borutoandroidcompose.ui.theme.COLLAPSED_BOTTOM_SHEET_RADIUS_LEVEL
import com.example.borutoandroidcompose.ui.theme.EXPANDED_BOTTOMSHEET_RADIUS_LEVEL
import com.example.borutoandroidcompose.ui.theme.INFO_BOX_ICON_SIZE
import com.example.borutoandroidcompose.ui.theme.LARGER_PADDING
import com.example.borutoandroidcompose.ui.theme.MEDIUM_PADDING
import com.example.borutoandroidcompose.ui.theme.MIN_BOTTOM_SHEET_HEIGHT
import com.example.borutoandroidcompose.ui.theme.titleColor
import com.example.borutoandroidcompose.utils.Constants
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController: NavHostController,
    selectedHero: Hero?,
    colors: Map<String, String>
) {

    // Color Palettes
    var vibrantColor by remember { mutableStateOf("#000000") }
    var darkVibrantColor by remember { mutableStateOf("#000000") }
    var onDarkVibrantColor by remember { mutableStateOf("#FFFFFF") }

    LaunchedEffect(key1 = selectedHero) {
        vibrantColor = colors[Target.VIBRANT.toString()]!!
        darkVibrantColor = colors[Target.DARK_VIBRANT.toString()]!!
        onDarkVibrantColor = colors["onDarkVibrant"]!!
    }

    // Adapt status bar color
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color(parseColor(darkVibrantColor))
    )


    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )
    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            COLLAPSED_BOTTOM_SHEET_RADIUS_LEVEL
        else
            EXPANDED_BOTTOMSHEET_RADIUS_LEVEL,
        label = ""
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_BOTTOM_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { hero ->
                DetailsBottomSheetContent(
                    selectedHero = hero,
                    infoBoxIconColor = Color(parseColor(vibrantColor)),
                    sheetBackgroundColor = Color(parseColor(darkVibrantColor)),
                    contentColor = Color(parseColor(onDarkVibrantColor))
                )
            }
        }
    ) { innerPadding ->
        selectedHero?.let { hero ->
            BackgroundImage(
                heroImage = hero.image,
                imageFraction = currentSheetFraction,
                backgroundColor = Color(parseColor(darkVibrantColor)),
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

@SuppressLint("Range")
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
                .fillMaxHeight(fraction = imageFraction + Constants.MIN_BOTTOMSHEET_BACKGROUND_IMAGE_FRACTION)
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

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress
        val currentValue = bottomSheetState.currentValue
        val targetValue = bottomSheetState.targetValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
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