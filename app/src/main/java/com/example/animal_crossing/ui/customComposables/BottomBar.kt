package com.example.animal_crossing.ui.customComposables

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animal_crossing.R
import com.example.animal_crossing.ui.navigation.NavDrawerItem
import com.example.animal_crossing.ui.navigation.Navigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(context: Context) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Navigation(navController = navController, context = context)
    }
}


@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController) {
    val screens = listOf(
        NavDrawerItem.BugsScreen,
        NavDrawerItem.FishScreen,
        NavDrawerItem.FossilsScreen,
        NavDrawerItem.SeaCreaturesScreen,
        NavDrawerItem.VillagersScreen,
        NavDrawerItem.GameDetailScreen,
        NavDrawerItem.FavoritesScreen
    )

    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "icon"
                    )
                },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
} // BottomBar

