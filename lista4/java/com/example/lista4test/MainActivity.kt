package com.example.lista4test

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generateData()
        setContent {
            Navigation2()
        }
    }
}

class Exercise constructor(c: String, p: Int)
{
    val content = c
    val points = p
}
class Subject constructor(n: String)
{
    val name = n
}
class ExerciseList constructor(e: MutableList<Exercise>, s: Subject, g: Int)
{
    val exercises = e
    val subject = s
    val grade = g
}
class SubjectGrade constructor(s: String, g: MutableList<Int>, n: Int)
{
    val subject = s
    val grades = g
    var listNumber = n
    var mean = 0

    fun getMean(){
        for(x in grades)
        {
            mean += x
        }
        mean /= grades.size
    }
}

val DataList = mutableListOf<ExerciseList>()
val DataListGradeMean = mutableListOf<SubjectGrade>()

fun generateData()
{
    DataListGradeMean.add(SubjectGrade("Matematyka", mutableListOf(),0))
    DataListGradeMean.add(SubjectGrade("PUM",mutableListOf(),0))
    DataListGradeMean.add(SubjectGrade("Fizyka",mutableListOf(),0))
    DataListGradeMean.add(SubjectGrade("Elektronika",mutableListOf(),0))
    DataListGradeMean.add(SubjectGrade("Algorytmy",mutableListOf(),0))

    val SubjectList = arrayOf<Subject>(Subject("Matematyka"),Subject("PUM"),Subject("Fizyka"),Subject("Elektronika"),Subject("Algorytmy"))
    for(x in 0..19)
    {
        val randGrade = Random.nextInt(3,5)
        val randSubject = Random.nextInt(0,4)
        val randNumOfExercise = Random.nextInt(1,10)
        val tempExerciseList = mutableListOf<Exercise>()
        for(y in 0..randNumOfExercise)
        {
            val randPoints = Random.nextInt(0,10)
            tempExerciseList.add(Exercise("Zadanie "+(y+1).toString(),randPoints))
        }
        DataList.add(ExerciseList(tempExerciseList,SubjectList[randSubject],randGrade))

        for(z in DataListGradeMean)
        {
            if(SubjectList[randSubject].name == z.subject)
            {
                z.grades.add(randGrade)
                z.listNumber +=1
            }
        }
    }

    for(x in DataListGradeMean)
        if(x.grades.size != 0) x.getMean()

    for (i in DataListGradeMean.indices.reversed())
    {
        if (DataListGradeMean[i].grades.size == 0) {
            DataListGradeMean.removeAt(i)
        }
    }
}

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object SecondScreen : Screens("second_screen")
    object ThridScreen : Screens("third")
    object FourthScreen : Screens("fourth")
}

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Third : BottomBar(Screens.ThridScreen.route, "Home", Icons.Default.List)
    object Fourth : BottomBar(Screens.FourthScreen.route, "First", Icons.Default.Info)
}

var argToPass = 0

@Composable
fun MainScreen(onSecondScreen: () -> Unit) {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text="Moje Listy Zadań", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(8.dp))
        ListyZadan(DataList,onSecondScreen)

    }
}

@Composable
fun SecondScreen() {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text= DataList[argToPass].subject.name, fontSize = 40.sp)
        Text(text= "Lista "+(argToPass+1).toString(), fontSize = 40.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Zadania(DataList[argToPass].exercises)
    }

}

@Composable
fun ThridScreen(){
    Navigation()
}


@Composable
fun FourthScreen(){
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text="Moje Oceny", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(8.dp))
        ListyOcen(DataListGradeMean)
    }

}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route){

            MainScreen{navController.navigate(Screens.SecondScreen.route + "/$argToPass")}
        }

        composable(route = Screens.SecondScreen.route + "/{arg}"){
            SecondScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation2(){
    val navController = rememberNavController()
    Scaffold(
            bottomBar = { BottomMenu(navController = navController) },
            content = { BottomNavGraph(navController = navController) },

    )
}

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.ThridScreen.route,
        modifier = Modifier.padding(bottom = 70.dp)
    ) {
        composable(route = Screens.ThridScreen.route){ ThridScreen() }
        composable(route = Screens.FourthScreen.route){ FourthScreen() }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.Third, BottomBar.Fourth
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListyZadan(words: MutableList<ExerciseList>,onSecondScreen: () -> Unit){
    LazyColumn{
        items(words.size){
            val word = remember {
                mutableStateOf(words[it])
            }
            Surface( onClick = {
                argToPass = it
                onSecondScreen() })
            {
                Column (modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
                    .border(2.dp, MaterialTheme.colorScheme.secondary, shape = RectangleShape)
                    .background(MaterialTheme.colorScheme.primary, shape = RectangleShape)
                    .padding(16.dp)){
                    Box(modifier =  Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.TopStart),
                            text = word.value.subject.name.toString(),
                            fontSize = 30.sp,
                            color = Color.White)
                        Text(
                            modifier = Modifier.align(Alignment.TopEnd),
                            text = "Lista " + (it+1).toString(),
                            fontSize = 30.sp,
                            color = Color.White)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 5.dp)){
                        Text(
                            modifier = Modifier.align(Alignment.TopStart),
                            text = "Liczba zadań: " + word.value.exercises.size.toString(),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Left,
                            color = Color.White)
                        Text(
                            modifier = Modifier.align(Alignment.TopEnd),
                            text = "Ocena: " + word.value.grade.toString(),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Right,
                            color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ListyOcen(words: MutableList<SubjectGrade>){
    LazyColumn{
        items(words.size){
            val word = remember {
                mutableStateOf(words[it])
            }

            Column (modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.secondary, shape = RectangleShape)
                .background(MaterialTheme.colorScheme.primary, shape = RectangleShape)
                .padding(16.dp)) {

                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = word.value.subject.toString(),
                        fontSize = 30.sp,
                        color = Color.White)
                    Text(
                        modifier = Modifier.align(Alignment.TopEnd),
                        text = "Średnia: " + word.value.mean,
                        fontSize = 30.sp,
                        color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = "Liczba list: " + word.value.listNumber.toString(),
                        fontSize = 20.sp,
                        color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Zadania(words: MutableList<Exercise>){
    LazyColumn{
        items(words.size){
            val word = remember {
                mutableStateOf(words[it])
            }
            Column(modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, MaterialTheme.colorScheme.secondary, shape = RectangleShape)
                .background(MaterialTheme.colorScheme.primary, shape = RectangleShape)
                .padding(16.dp)) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = "Zadane " + (it + 1).toString(),
                        fontSize = 30.sp,
                        color = Color.White)
                    Text(
                        modifier = Modifier.align(Alignment.TopEnd),
                        text = "pkt: " + word.value.points.toString(),
                        fontSize = 30.sp,
                        color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = word.value.content,
                        fontSize = 25.sp,
                        color = Color.White)

                }
            }
        }
    }
}
