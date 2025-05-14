package com.iskan.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iskan.ui.R
import com.iskan.ui.theme.LightOrange
import com.iskan.ui.theme.ModerateBlue
import com.iskan.ui.theme.VeryDarkGrayishBlue
import com.iskan.ui.theme.VividRed

@Composable
fun JelloButtonPrimary(
    text: String = "Login Now",
    onClick: () -> Unit = {},
    enabled: Boolean = true,
) {
    JelloBaseButton(
        text = text,
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        color = ButtonDefaults.buttonColors(
            containerColor = LightOrange,
            contentColor = VeryDarkGrayishBlue
        )
    )
}

@Preview
@Composable
fun JelloButtonPrimaryPreview() {
    JelloButtonPrimary()
}

@Composable
fun JelloButtonFacebook(
    text: String = "Facebook",
    onClick: () -> Unit = {},
    modifer: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(56.dp)
) {
    JelloWithIconBaseButton(
        text = text,
        onClick = onClick,
        enabled = true,
        modifier = modifer,
        colors = ButtonDefaults.buttonColors(
            containerColor = ModerateBlue,
            contentColor = Color.White
        ),
        srcIcon = R.drawable.ic_facebook,
        descIcon = "Facebook"
    )
}

@Preview
@Composable
fun JelloButtonFacebookPreview() {
    JelloButtonFacebook()
}

@Composable
fun JelloButtonGoogle(
    text: String = "Google",
    onClick: () -> Unit = {},
    modifer : Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(56.dp)
) {
    JelloWithIconBaseButton(
        text = text,
        onClick = onClick,
        enabled = true,
        modifier = modifer,
        colors = ButtonDefaults.buttonColors(
            containerColor = VividRed,
            contentColor = Color.White
        ),
        srcIcon = R.drawable.ic_google,
        descIcon = "Google"
    )
}

@Preview
@Composable
fun JelloButtonGooglePreview() {
    JelloButtonGoogle()
}

@Composable
fun JelloButtonSosmedRow(
    onClickGoogle: () -> Unit = {},
    onClickFacebook: () -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
        .padding(16.dp),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        JelloButtonGoogle(
            modifer = Modifier
                .height(56.dp)
                .weight(1f),
            onClick = onClickGoogle
        )

        JelloButtonFacebook(
            modifer = Modifier
                .height(56.dp)
                .weight(1f),
            onClick = onClickFacebook
        )
    }
}

@Preview
@Composable
fun JelloButtonSosmedRowPreview() {
    JelloButtonSosmedRow()
}