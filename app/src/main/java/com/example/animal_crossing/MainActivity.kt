package com.example.animal_crossing

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animal_crossing.ui.navigation.NavDrawerItem
import com.example.animal_crossing.ui.navigation.Navigation
import com.example.animal_crossing.ui.screens.RegisterUI
import com.example.animal_crossing.ui.theme.AnimalCrossingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.animal_crossing.data.api.viewModel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalCrossingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    //val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
//            TopBar(scope = scope, scaffoldState = scaffoldState)
            TopBarTwo()
        },
//        drawerBackgroundColor = MaterialTheme.colors.primary,
//        drawerContent = {
//            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
//        }
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun TopBarTwo() {
    TopAppBar(
        title = { Text(text = "Animal Crossing", fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

@Composable
fun DrawerItem(item: NavDrawerItem, selected: Boolean, onItemClick: (NavDrawerItem) -> Unit) {
    val background = if (selected) R.color.purple_700 else android.R.color.transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "Home",
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            "Home",
            fontSize = 18.sp,
            color = Color.White
        )
    }
} // DrawerItem

@Composable
fun BottomBar(modifier: Modifier = Modifier, navController: NavController) {
    val screens = listOf(
        NavDrawerItem.BugsScreen,
        NavDrawerItem.FishScreen,
        NavDrawerItem.FossilsScreen,
        NavDrawerItem.SeaCreaturesScreen,
        NavDrawerItem.VillagersScreen,
        NavDrawerItem.LoginScreen,
        NavDrawerItem.RegisterScreen,
        NavDrawerItem.GameDetailScreen
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
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "icon"
                    )
                },
                label = { Text(text = screen.route) },
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimalCrossingTheme {
        Greeting("Android")
    }
}