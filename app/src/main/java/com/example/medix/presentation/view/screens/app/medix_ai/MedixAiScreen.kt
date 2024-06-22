package com.example.medix.presentation.view.screens.app.medix_ai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medix.R
import com.example.medix.presentation.view.components.ElevatedButton
import com.example.medix.presentation.view.components.TopBar
import com.example.medix.ui.theme.blackText
import com.example.medix.ui.theme.lightBackground
import com.example.medix.ui.theme.lightMixture
import com.example.medix.ui.theme.mixture

@Composable
fun MedixAiScreen(
    navigateUp: () -> Unit,
    navController: NavController,
    viewModel: MedixAiViewModel = hiltViewModel(),
) {

    /*val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                onImageSelected(it)
                viewModel.predictImage(it, contentResolver)
            }
        }
    )*/
    val textFieldValue = remember { mutableStateOf(viewModel.imageUrl.value) }
    //val resultState = viewModel.result.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(lightBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(12.dp, shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .height(80.dp)
                .background(mixture),
            contentAlignment = Alignment.Center
        ) {
            TopBar(
                title = "Medix AI",
                onBackClick = navigateUp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(100.dp))
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .background(lightMixture)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.report_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .shadow(30.dp)
                        .padding(start = 15.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Add a medical report.",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                color = blackText
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "A detailed health report helps the Ai model to diagnose you better.",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = blackText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            TextField(
                value = textFieldValue.value,
                onValueChange = { newValue -> textFieldValue.value = newValue },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(50.dp))

            ElevatedButton(
                text = "Add Report",
                textSize = 20.sp,
                textColor = Color.White,
                backgroundColor = mixture,
                padding = PaddingValues(10.dp),
                onClick = {
                    /*photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )*/
                    if (textFieldValue.value.isNotEmpty()) {
                        viewModel.imageUrl.value = textFieldValue.value
                        viewModel.predictImage(navController)
                    }
                }
            )

            //Text(text = "Result: $resultState")
        }
    }
}
