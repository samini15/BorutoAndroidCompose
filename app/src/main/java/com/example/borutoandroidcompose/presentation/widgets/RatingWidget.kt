package com.example.borutoandroidcompose.presentation.widgets

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.ui.theme.LightGray
import com.example.borutoandroidcompose.ui.theme.SMALL_PADDING
import com.example.borutoandroidcompose.ui.theme.StarColor

const val FILLED_STARS = "filledStars"
const val HALF_FILLED_STARS = "halfFilledStars"
const val EMPTY_STARS = "emptyStars"
@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    spaceBetween: Dp = SMALL_PADDING,
    scaleFactor: Float = 3f
) {
    val result = CalculateStars(rating = rating)

    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        result[FILLED_STARS]?.let {
            repeat(it) {
                FilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
        result[HALF_FILLED_STARS]?.let {
            repeat(it) {
                HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
        result[EMPTY_STARS]?.let {
            repeat(it) {
                EmptyStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = scaleFactor)
            }
        }
    }
}

@Composable
fun FilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = StarColor)
            }
        }
    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = LightGray.copy(alpha = 0.5f))
                clipPath(path = starPath) {
                    drawRect(color = StarColor, size = Size(
                        width = starPathBounds.maxDimension / 1.7f,
                        height = starPathBounds.maxDimension
                    ))
                }
            }
        }
    }
}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size

        scale(scale = scaleFactor) {
            val pathWidth = starPathBounds.width
            val pathHeight = starPathBounds.height

            val left = (canvasSize.width / 2f) - (pathWidth / 1.7f)
            val top = (canvasSize.height / 2f) - (pathHeight / 1.7f)

            translate(left = left, top = top) {
                drawPath(path = starPath, color = LightGray.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
fun CalculateStars(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfFilledStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (leftNumber, rightNumber) = rating.toString()
            .split(".")
            .map { it.toInt() }

        if (leftNumber in 0..5 && rightNumber in 0..9) {
            filledStars = leftNumber
            when (rightNumber) {
                in 1..5 -> halfFilledStars++
                in 5..9 -> filledStars++
            }

            if (leftNumber == 5 && rightNumber > 0) { // Invalid
                emptyStars = 5
                filledStars = 0
                halfFilledStars = 0
            }
        } else {
            Log.d("RatingWidget", "Invalid rating number")
        }
    }
    emptyStars = maxStars - (filledStars + halfFilledStars)
    return mapOf(
        FILLED_STARS to filledStars,
        HALF_FILLED_STARS to halfFilledStars,
        EMPTY_STARS to emptyStars
    )
}

@Preview(showBackground = true)
@Composable
fun RatingWidgetPreview() {
    RatingWidget(modifier = Modifier, rating = 2.4)
}

@Preview(showBackground = true)
@Composable
fun HalfFilledStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}

@Preview(showBackground = true)
@Composable
fun EmptyStarPreview() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }
    EmptyStar(starPath = starPath, starPathBounds = starPathBounds, scaleFactor = 2f)
}