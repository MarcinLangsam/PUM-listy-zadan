package com.example.lista2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lista2.databinding.ActivityMainBinding
import kotlin.random.Random

class Exercise constructor(c: String, p: Int)
{
    val content = c
    val points = p
}

class Subject constructor(n: String)
{
    val name = n
}

class ExerciseList(e: MutableList<Exercise>, s: Subject, g: Int)
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

fun generate_data()
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
            val randPoints = Random.nextInt(1,10)
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


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ListyZadan())
        generate_data()

        binding.bottomNavigationView.setOnItemSelectedListener()
        {
            when(it.itemId)
            {
                R.id.listy_zadan -> replaceFragment(ListyZadan())
                R.id.oceny->replaceFragment(Oceny())
                else->{}
            }
            true
        }
    }


    fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }


}