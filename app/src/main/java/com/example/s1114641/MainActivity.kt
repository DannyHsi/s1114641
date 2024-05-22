package com.example.s1114641

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.s1114641.ui.theme.S1114641Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1114641Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                    ) {
                    //Greeting()
                    Main()
                    //Animation()

                }
            }
        }
    }
}
@Composable
fun FirstScreen(navController: NavController) {
    var title by remember { mutableStateOf("瑪利亞基金會服務總覽") }
    Column(
        modifier = Modifier
    ) {
        Text(text = title, color = Color.Blue)
        var appear by remember { mutableStateOf(true) }
        Column {

            AnimatedVisibility(
                visible = appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.service),
                    contentDescription = "服務總覽"
                )
            }

            AnimatedVisibility(
                visible = !appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.danny),
                    contentDescription = "xiaoze", modifier = Modifier.size(300.dp)
                )
            }
            Button(onClick = { appear = !appear }
            ) {
                if (appear) {
                    Text(text = "作者:資管二A習祐翔")
                    title = "瑪利亞基金會服務總覽"
                } else {
                    Text(text = "服務總覽")
                    title = "關於app作者"
                }
            }
        }
    }
}




@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Text(text = "主要機構", color = Color.Red)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    var showMenu by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "maria"
                )
            },
            actions = {
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpFirst") })

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpSecond") })
                }
            }

        )


        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}