package com.example.animal_crossing.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animal_crossing.data.api.viewModel.*
import com.example.animal_crossing.ui.screens.*

@Composable
fun Navigation(){
    val navigationController = rememberNavController()
    val bugViewModel = BugViewModel()
    val fishViewModel = FishViewModel()
    val fossilsViewModel = FossilViewModel()
    val seaCreaturesViewModel = SeaCreatureViewModel()
    val villagersViewModel = VillagerViewModel()

    NavHost(navController = navigationController, startDestination = Screen.LoginScreen.route){
        composable(Screen.BugsScreen.route){
            BugsScreen(navigationController, bugViewModel)
        }
        composable(Screen.DetailedBugScreen.route){
            DetailedBugScreen(vm = bugViewModel)
        }
        composable(Screen.FishScreen.route){
            FishScreen(navigationController, fishViewModel)
        }
        composable(Screen.DetailedFishScreen.route){
            DetailedFishScreen(vm = fishViewModel)
        }
        composable(Screen.FossilsScreen.route){
            FossilScreen(navigationController, fossilsViewModel)
        }
        composable(Screen.DetailedFossilsScreen.route){
            DetailedFossilsScreen(vm = fossilsViewModel)
        }
        composable(Screen.SeaCreaturesScreen.route){
            SeaCreatureScreen(navigationController, seaCreaturesViewModel)
        }
        composable(Screen.DetailedSeaCreaturesScreen.route){
            DetailedSeaCreaturesScreen(vm = seaCreaturesViewModel)
        }
        composable(Screen.VillagersScreen.route){
            VillagerScreen(navigationController, villagersViewModel)
        }
        composable(Screen.DetailedVillagersScreen.route){
            DetailedVillagersScreen(vm = villagersViewModel)
        }

        composable(Screen.GameDetailScreen.route){
            GameDetailView(navigationController = navigationController)
        }

        composable(Screen.LoginScreen.route){
            LoginUI(onSuccessfulLogin = { /*TODO*/ }, navigationController = navigationController)
        }

        composable(Screen.RegisterScreen.route){
            RegisterUI(onSuccessfulLogin = { /*TODO*/ }, navigationController = navigationController)
        }
    }
}

sealed class Screen(val route: String){
    object BugsScreen: Screen("bugs_screen")
    object DetailedBugScreen: Screen("detailed_bug_screen")
    object FishScreen: Screen("fish_screen")
    object DetailedFishScreen: Screen("detailed_fish_screen")
    object FossilsScreen: Screen("fossils_screen")
    object DetailedFossilsScreen: Screen("detailed_fossils_screen")
    object SeaCreaturesScreen: Screen("sea_creatures_screen")
    object DetailedSeaCreaturesScreen: Screen("detailed_sea_creatures_screen")
    object VillagersScreen: Screen("villagers_screen")
    object DetailedVillagersScreen: Screen("detailed_villagers_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object GameDetailScreen: Screen("game_detail_screen")

}
