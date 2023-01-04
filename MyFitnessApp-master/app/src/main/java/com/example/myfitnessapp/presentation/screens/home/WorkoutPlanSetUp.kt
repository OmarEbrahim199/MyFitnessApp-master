package com.example.myfitnessapp.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.myfitnessapp.presentation.shared.viewmodel.WorkoutViewModel
import java.time.DayOfWeek
import com.example.myfitnessapp.R
import com.example.myfitnessapp.domain.models.WorkoutPlan
import com.example.myfitnessapp.presentation.shared.components.RegularButton
import com.example.myfitnessapp.navigation.Screens
import com.example.myfitnessapp.ui.theme.*

@Composable
fun WorkoutPlanSetUpScreen(workoutViewModel: WorkoutViewModel, navController: NavHostController) =

    with(workoutViewModel) {

        var workoutPlanName by remember { mutableStateOf("") }

        Surface(modifier = Modifier.fillMaxSize(), color = myDarkBlue) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {

                Heading(
                    text = stringResource(R.string.set_up_workout_plan_heading),
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp)
                )

                SubHeading(
                    text = stringResource(R.string.choose_a_name), modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                TextField(
                    value = workoutPlanName,
                    onValueChange = { workoutPlanName = it },
                    modifier = Modifier.padding(horizontal = 5.dp),
                    textStyle = TextStyle(fontSize = 18.sp, color = myWhite)
                )

                SubHeading(
                    text = stringResource(R.string.select_training_days),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                LazyRow() {
                    DayOfWeek.values().forEach {

                        item {

                            WeekdaysChipItem(day = it, onCheck = { day, checked ->

                                if (checked) addDay(day) else removeDay(
                                    day
                                )


                            })
                        }

                    }
                }



                SubHeading(text = stringResource(R.string.duration), modifier = Modifier.padding(horizontal = 5.dp))

                var duration by remember { mutableStateOf(0) }

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    NumberPicker(
                        value = duration,
                        onValueChange = { duration = it },
                        range = 10..120,
                        textStyle = TextStyle(color = myWhite),
                        dividersColor = myGreen,
                        modifier = Modifier.width(100.dp)
                    )
                    Title(text = stringResource(R.string.minutes))
                }

                RegularButton(
                    text = stringResource(R.string.save),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    if (workoutPlanName.isNotEmpty() && selectedDays.isNotEmpty()) {
                        val workoutPlan = WorkoutPlan(
                            name = workoutPlanName,
                            workouts = selectedDays.toList() as ArrayList<DayOfWeek>,
                            duration = duration
                        )
                        addWorkoutPlan(workoutPlan)
                        navController.navigate(Screens.Home.route)
                    }
                }
            }


        }


    }

@Composable
fun WeekdaysChipItem(
    modifier: Modifier = Modifier,
    onCheck: (DayOfWeek, Boolean) -> Unit,
    day: DayOfWeek
) {

    val text = day.name.substring(0..1).uppercase()


    var checked by remember {
        mutableStateOf(false)
    }
    var backgroundColor by remember { mutableStateOf(myLightBlue) }

    backgroundColor = if (checked) myGreen else veryDarkBlue.copy(0.6f)

    var textColor by remember {
        mutableStateOf(Color.Black)
    }


    textColor = if (checked) Color.Black else myWhite

    Surface(color = backgroundColor, shape = CircleShape, modifier = modifier
        .clickable {
            checked = !checked
            onCheck(day, checked)
        }
        .size(50.dp)
        .padding(5.dp),
        elevation = 10.dp) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(text = text, color = textColor)

        }


    }
}





