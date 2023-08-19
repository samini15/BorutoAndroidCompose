package com.example.borutoandroidcompose.presentation.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoandroidcompose.R
import com.example.borutoandroidcompose.ui.theme.INFO_BOX_ICON_SIZE
import com.example.borutoandroidcompose.ui.theme.MEDIUM_PADDING
import com.example.borutoandroidcompose.ui.theme.titleColor

@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    descriptionText: String,
    textColor: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .padding(end = MEDIUM_PADDING)
                .size(INFO_BOX_ICON_SIZE),
            painter = icon,
            contentDescription = "Info box icon",
            tint = iconColor
        )

        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = descriptionText,
                color = textColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoBoxPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_cake),
        iconColor = MaterialTheme.colorScheme.primary,
        bigText = "77",
        descriptionText = "Power",
        textColor = MaterialTheme.colorScheme.titleColor
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun InfoBoxDarkPreview() {
    InfoBox(
        icon = painterResource(id = R.drawable.ic_cake),
        iconColor = MaterialTheme.colorScheme.primary,
        bigText = "77",
        descriptionText = "Power",
        textColor = MaterialTheme.colorScheme.titleColor
    )
}