package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.AuthViewModel
import com.example.animal_crossing.ui.customComposables.BottomBar
import com.example.animal_crossing.ui.customComposables.CustomImageCard
import com.example.animal_crossing.ui.navigation.NavDrawerItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameDetailView(
    authViewModel: AuthViewModel = viewModel(),
    navigationController: NavHostController
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        BoxWithConstraints {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row {
                                Text(
                                    text = "About the Game"
                                )
                            }
                        }
                    )
                },
                bottomBar = { BottomBar(navController = navigationController) },

                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                            .padding(bottom = 60.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        CustomImageCard(
                            imageUrl = "https://assets.nintendo.com/image/upload/ar_16:9,b_auto:border,c_lpad/b_white/f_auto/q_auto/dpr_1.0/c_scale,w_1200/ncom/en_US/dlc/switch-dlc/animal-crossing-new-horizons-dlc/individual/animal-crossing-new-horizons-happy-home-paradise/image",
                            cardTitle = ""
                        )

                        // Space between
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )

                        Text(
                            text = "Animal Crossing: New Horizons is a social simulation game developed by Nintendo. The player takes on the role of a human character who moves to a deserted island and starts a new life there. "
                        )

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(
                            text = "The player takes on the role of a human character who moves to a deserted island and starts a new life there. "
                        )

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(
                            text = "The game revolves around gathering resources, crafting items, and building a community with the animal residents of the island."
                        )

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "Different activities and events happen depending on the time of day and season, with different fish, bugs, and fossils available to find.")

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "The game also has various holidays and special events throughout the year, like Halloween and Christmas, which have their own unique activities and decorations.")

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "One of the main features of Animal Crossing: New Horizons is its social aspect. Players can interact with the animal residents of the island, who each have their own unique personalities and interests. ")

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "he player can complete tasks for them, trade items, and even invite them to live on the island permanently. Players can also visit other players' islands and trade items with them online.")

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "Animal Crossing: New Horizons is known for its relaxing, laid-back gameplay and its cute and charming aesthetics. ")

                        Divider(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))

                        Text(text = "he game has become a cultural phenomenon, especially during the COVID-19 pandemic, as it provided a way for people to socialize and escape to a virtual world during lockdowns and social distancing measures.")

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )

                        Button(
                            onClick = {
                                authViewModel.performLogout()
                                navigationController.navigate(NavDrawerItem.LoginScreen.route)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Sign Out")
                        }

                    }
                }
            )

        }
    }
}