package com.example.wefit_teste.presentation.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.wefit_teste.R
import com.example.wefit_teste.common.AppColors
import java.text.SimpleDateFormat
import java.util.*
import com.example.wefit_teste.common.Spacings

@Composable
fun SuccessScreen(
    onNavigateHome: () -> Unit
) {
    val currentTime by remember {
        mutableStateOf(
            SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault()).format(Date())
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BackgroundDark)
            .padding(Spacings.spacing(16)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(Spacings.spacing(12)),
            colors = CardDefaults.cardColors(containerColor = AppColors.White),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(Spacings.spacing(24))
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Compra realizada em $currentTime",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = AppColors.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(Spacings.spacing(8)))

                Text(
                    text = "Compra realizada com sucesso!",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = AppColors.Black
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(Spacings.spacing(24)))

                Image(
                    painter = painterResource(id = R.drawable.success_purchase),
                    contentDescription = "Compra Realizada",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(Spacings.spacing(24)))

                Button(
                    onClick = onNavigateHome,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.PrimaryBlue,
                        contentColor = AppColors.White
                    ),
                    shape = RoundedCornerShape(Spacings.spacing(8)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Spacings.spacing(48))
                ) {
                    Text(text = "Voltar à Home")
                }
            }
        }
    }
}
