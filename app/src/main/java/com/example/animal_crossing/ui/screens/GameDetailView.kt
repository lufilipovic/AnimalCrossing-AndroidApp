package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.AuthViewModel
import com.example.animal_crossing.ui.navigation.NavDrawerItem
import com.example.animal_crossing.ui.customComposables.CustomImageCard

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameDetailView(authViewModel: AuthViewModel = viewModel(), navigationController: NavHostController){
    


       Scaffold(
           topBar = {
               TopAppBar(
                   title = { Row { Text(
                       text = "About the Game",
                       textAlign = TextAlign.Center,
                       fontSize = 40.sp
                   ) } }
               )
               // Space between
               Spacer(
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(90.dp)
               )
           },
           content = {

               Column() {
                   CustomImageCard(imageUrl = "https://assets.nintendo.com/image/upload/ar_16:9,b_auto:border,c_lpad/b_white/f_auto/q_auto/dpr_1.0/c_scale,w_1200/ncom/en_US/dlc/switch-dlc/animal-crossing-new-horizons-dlc/individual/animal-crossing-new-horizons-happy-home-paradise/image",
                       cardTitle = "")

                   // Space between
                   Spacer(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(30.dp)
                   )


                   Text(
                       text = "Animal Crossing: New Horizons is a social simulation game developed by Nintendo. The player takes on the role of a human character who moves to a deserted island and starts a new life there. The game revolves around gathering resources, crafting items, and building a community with the animal residents of the island. The game is played in real-time, with different events and activities happening depending on the time of day and season.",
                   )

                   // Space between
                   Spacer(
                       modifier = Modifier
                           .fillMaxWidth()
                           .height(30.dp)
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