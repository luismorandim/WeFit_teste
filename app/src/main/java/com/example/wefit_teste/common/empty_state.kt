package com.example.wefit_teste.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wefit_teste.R
import com.example.wefit_teste.common.AppColors
import com.example.wefit_teste.common.Spacings

@Composable
fun EmptyState(
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacings.spacing(24)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(Spacings.spacing(16)))

        Text(
            text = "Parece que não há nada aqui :(",
            color = AppColors.Black,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(Spacings.spacing(12)))

        Image(
            painter = painterResource(id = R.drawable.ic_empty_state),
            contentDescription = "Empty State",
            modifier = Modifier.size(500.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(Spacings.spacing(60)))

        Button(
            onClick = onButtonClick,
            shape = RoundedCornerShape(Spacings.spacing(8)),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColors.PrimaryBlue,
                contentColor = AppColors.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = buttonText)
        }
    }
}
