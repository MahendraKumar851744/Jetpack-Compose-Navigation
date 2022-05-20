package com.example.navigationdemoyt

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdemoyt.ui.theme.NavigationDemoYTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoYTTheme {
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


@Composable
fun Screen1(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Android Programmers", fontSize = 24.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { navController.navigate(Routes.Screen2.route) }) {
            Text(text = "Screen2")

        }

    }
}

@Composable
fun Screen2(navController: NavHostController){
    var FirstName by remember {
        mutableStateOf("")
    }
    var LastName by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = FirstName , onValueChange = {FirstName = it} )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = LastName , onValueChange = {LastName = it} )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Routes.Screen3.route + "/$FirstName/$LastName") }) {
            Text(text = "Screen3")

        }
    }

}

@Composable
fun Screen3(fname:String?,lname:String?){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
       Text(text = "$fname $lname")
    }
}

@Composable
fun MainScreen(){
    val navController  = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Screen1.route ){
       composable(Routes.Screen1.route){
           Screen1(navController)
       }
        composable(Routes.Screen2.route){
            Screen2(navController)
        }
        composable(Routes.Screen3.route+"/{fn}/{ln}"){
            val fname = it.arguments?.getString("fn")
            val lname = it.arguments?.getString("ln")
            Screen3(fname,lname)
        }
    }
}



