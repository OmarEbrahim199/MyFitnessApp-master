package com.example.myfitnessapp.presentation.screens.profile
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import com.example.myfitnessapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myfitnessapp.presentation.shared.components.RegularButton
import com.example.myfitnessapp.presentation.screens.home.SubHeading
import com.example.myfitnessapp.presentation.screens.home.Title
import com.example.myfitnessapp.presentation.shared.viewmodel.UserViewModel
import com.example.myfitnessapp.presentation.shared.viewmodel.WorkoutViewModel
import com.example.myfitnessapp.ui.theme.myDarkBlue
import com.example.myfitnessapp.ui.theme.myGreen
import com.example.myfitnessapp.ui.theme.myWhite


@Composable
fun ProfileScreen
            (navController: NavHostController = rememberNavController(),
             userViewModel: UserViewModel,
             workoutViewModel: WorkoutViewModel = viewModel(),
) = with(workoutViewModel){

    var openLogoutDialog by remember { mutableStateOf(false) }
    val notification = rememberSaveable {mutableStateOf("")}
    if (notification.value.isNotEmpty()){
        notification.value = ""
    }


    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp)) {


        val imageUri = rememberSaveable{ mutableStateOf("") }
        val painter = rememberImagePainter(
            if (imageUri.value.isEmpty())
                R.drawable.ic_user
            else
                imageUri.value
        )

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ){ uri: Uri? ->
            uri?.let { imageUri.value = it.toString() }
        }

        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Card(shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(180.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .wrapContentSize()
                      .clickable { launcher.launch("image/*") },
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = user?.userName.toString(), color = myWhite, fontSize = 30.sp )
            RegularButton(
                Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                stringResource(R.string.logout ),
                onClick = {
                   openLogoutDialog = true
                }
            )
        }
    }





    if (openLogoutDialog) {

        Dialog(
            onDismissRequest = { openLogoutDialog = false },
            //properties = DialogProperties(usePlatformDefaultWidth = false)

        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 15.dp),
                color = myDarkBlue,
                elevation = 20.dp,

                ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {

                    SubHeading(text = stringResource(R.string.logout), color = myGreen)

                    Title(
                        text = stringResource(R.string.logout_alert_text),

                        )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            20.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {

                        RegularButton(text = stringResource(R.string.confirm), onClick = {
                            userViewModel.logOut()
                            openLogoutDialog = false
                            navController.navigateUp()

                        })

                        RegularButton(text = stringResource(R.string.dismiss), onClick = {
                            openLogoutDialog = false
                        })

                    }


                }

            }

        }

    }

}

