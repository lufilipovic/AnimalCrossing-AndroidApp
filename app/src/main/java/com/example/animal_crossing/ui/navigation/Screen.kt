package com.example.animal_crossing.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animal_crossing.data.api.viewModel.*
import com.example.animal_crossing.ui.screens.*

@Composable
fun Navigation(navController: NavHostController) {
    //val navController = rememberNavController()
    val bugViewModel = BugViewModel()
    val fishViewModel = FishViewModel()
    val fossilsViewModel = FossilViewModel()
    val seaCreaturesViewModel = SeaCreatureViewModel()
    val villagersViewModel = VillagerViewModel()

    NavHost(navController = navController, startDestination = NavDrawerItem.LoginScreen.route) {
        composable(NavDrawerItem.BugsScreen.route){
            BugsScreen(navController, bugViewModel)
        }
        composable(NavDrawerItem.DetailedBugScreen.route){
            DetailedBugScreen(vm = bugViewModel)
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
sealed class NavDrawerItem(val route: String){
    object BugsScreen: NavDrawerItem("bugs_screen")
    object DetailedBugScreen: NavDrawerItem("detailed_bug_screen")
    object FishScreen: NavDrawerItem("fish_screen")
    object DetailedFishScreen: NavDrawerItem("detailed_fish_screen")
    object FossilsScreen: NavDrawerItem("fossils_screen")
    object DetailedFossilsScreen: NavDrawerItem("detailed_fossils_screen")
    object SeaCreaturesScreen: NavDrawerItem("sea_creatures_screen")
    object DetailedSeaCreaturesScreen: NavDrawerItem("detailed_sea_creatures_screen")
    object VillagersScreen: NavDrawerItem("villagers_screen")
    object DetailedVillagersScreen: NavDrawerItem("detailed_villagers_screen")
    object LoginScreen: NavDrawerItem("login_screen")
    object RegisterScreen: NavDrawerItem("register_screen")
    object GameDetailScreen: NavDrawerItem("game_detail_screen")
}