package com.example.animal_crossing.ui.navigation

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animal_crossing.R
import com.example.animal_crossing.data.api.viewModel.*
import com.example.animal_crossing.ui.screens.*

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    //val navController = rememberNavController()
    val bugViewModel = BugViewModel()
    val fishViewModel = FishViewModel()
    val fossilsViewModel = FossilViewModel()
    val seaCreaturesViewModel = SeaCreatureViewModel()
    val villagersViewModel = VillagerViewModel()

    val sharedPreferences = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

    NavHost(navController = navController, startDestination = NavDrawerItem.LoginScreen.route) {
        composable(NavDrawerItem.BugsScreen.route){
            BugsScreen(navController, bugViewModel)
        }
        composable(NavDrawerItem.DetailedBugScreen.route){
            DetailedBugScreen(vm = bugViewModel, sharedPreferences = sharedPreferences)
        }
        composable(NavDrawerItem.FishScreen.route){
            FishScreen(navController, fishViewModel)
        }
        composable(NavDrawerItem.DetailedFishScreen.route){
            DetailedFishScreen(vm = fishViewModel)
        }
        composable(NavDrawerItem.FossilsScreen.route){
            FossilScreen(navController, fossilsViewModel)
        }
        composable(NavDrawerItem.DetailedFossilsScreen.route){
            DetailedFossilsScreen(vm = fossilsViewModel)
        }
        composable(NavDrawerItem.SeaCreaturesScreen.route){
            SeaCreatureScreen(navController, seaCreaturesViewModel)
        }
        composable(NavDrawerItem.DetailedSeaCreaturesScreen.route){
            DetailedSeaCreaturesScreen(vm = seaCreaturesViewModel)
        }
        composable(NavDrawerItem.VillagersScreen.route){
            VillagerScreen(navController, villagersViewModel)
        }
        composable(NavDrawerItem.DetailedVillagersScreen.route){
            DetailedVillagersScreen(vm = villagersViewModel)
        }

        composable(NavDrawerItem.GameDetailScreen.route){
            GameDetailView(navigationController = navController)
        }

        composable(NavDrawerItem.LoginScreen.route){
            LoginUI(onSuccessfulLogin = { /*TODO*/ }, navigationController = navController)
        }

        composable(NavDrawerItem.RegisterScreen.route){
            RegisterUI(onSuccessfulLogin = { /*TODO*/ }, navigationController = navController)
        }
    }
} // Navigation
sealed class NavDrawerItem(val route: String, var icon: Int, var title: String){
    object BugsScreen: NavDrawerItem("bugs_screen", icon = R.drawable.bug, title = "Bugs")
    object DetailedBugScreen: NavDrawerItem("detailed_bug_screen", icon = R.drawable.bug, title = "Bugs")
    object FishScreen: NavDrawerItem("fish_screen", icon = R.drawable.fish, title = "Fish")
    object DetailedFishScreen: NavDrawerItem("detailed_fish_screen", icon = R.drawable.fish, title = "Fish")
    object FossilsScreen: NavDrawerItem("fossils_screen", icon = R.drawable.fossil, title = "Fossil")
    object DetailedFossilsScreen: NavDrawerItem("detailed_fossils_screen", icon = R.drawable.fossil, title = "Fossil")
    object SeaCreaturesScreen: NavDrawerItem("sea_creatures_screen", icon = R.drawable.turtle, title = "Sea")
    object DetailedSeaCreaturesScreen: NavDrawerItem("detailed_sea_creatures_screen", icon = R.drawable.turtle, title = "Sea")
    object VillagersScreen: NavDrawerItem("villagers_screen", icon = R.drawable.villager, title = "Villager")
    object DetailedVillagersScreen: NavDrawerItem("detailed_villagers_screen", icon = R.drawable.villager, title = "Villagers")
    object LoginScreen: NavDrawerItem("login_screen", icon = R.drawable.ic_home, title = "Login")
    object RegisterScreen: NavDrawerItem("register_screen", icon = R.drawable.ic_home, title = "Register")
    object GameDetailScreen: NavDrawerItem("game_detail_screen", icon = R.drawable.info, title = "Info")
}