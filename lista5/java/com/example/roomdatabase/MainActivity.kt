package com.example.roomdatabase

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.adapters.FridayListAdapter
import com.example.roomdatabase.adapters.MondayListAdapter
import com.example.roomdatabase.adapters.NoteListAdapter
import com.example.roomdatabase.adapters.TaskListAdapter
import com.example.roomdatabase.adapters.ThursdayListAdapter
import com.example.roomdatabase.adapters.TuesdayListAdapter
import com.example.roomdatabase.adapters.WednesdayListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

var EditCode = 8
var id = 0
var name = ""
var time = ""
var des = ""

class WordsApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { GradeRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database.Dao()) }
}

class MainActivity : AppCompatActivity() {

    private var InsertCode = 1
    private val gradeViewModel: GradeViewModel by viewModels {
        GradeViewModelFactory((application as WordsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapterPlan = MondayListAdapter(gradeViewModel, this@MainActivity)
        val adapterPlanTuesday = TuesdayListAdapter(gradeViewModel, this@MainActivity)
        val adapterPlanWednesday = WednesdayListAdapter(gradeViewModel, this@MainActivity)
        val adapterPlanThursday = ThursdayListAdapter(gradeViewModel, this@MainActivity)
        val adapterPlanFriday = FridayListAdapter(gradeViewModel, this@MainActivity)
        val adapterTasks = TaskListAdapter(gradeViewModel, this@MainActivity)
        val adapterNotes = NoteListAdapter(gradeViewModel, this@MainActivity)
        recyclerView.adapter = adapterPlan
        recyclerView.layoutManager = LinearLayoutManager(this)


        gradeViewModel.allMonday.observe(this) { grades ->
            grades.let { adapterPlan.submitList(it) }
        }
        gradeViewModel.allTuesday.observe(this) { grades ->
            grades.let { adapterPlanTuesday.submitList(it) }
        }
        gradeViewModel.allWednesday.observe(this) { grades ->
            grades.let { adapterPlanWednesday.submitList(it) }
        }
        gradeViewModel.allThursday.observe(this) { grades ->
            grades.let { adapterPlanThursday.submitList(it) }
        }
        gradeViewModel.allFriday.observe(this) { grades ->
            grades.let { adapterPlanFriday.submitList(it) }
        }
        gradeViewModel.allTask.observe(this) { grades ->
            grades.let { adapterTasks.submitList(it) }
        }
        gradeViewModel.allNotes.observe(this) { grades ->
            grades.let { adapterNotes.submitList(it) }
        }


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            if(recyclerView.adapter in arrayOf(adapterPlan,adapterPlanTuesday,adapterPlanWednesday,adapterPlanThursday,adapterPlanFriday))
            {
                val intent = Intent(this@MainActivity, NewWeekActivity::class.java)
                startActivityForResult(intent, InsertCode)
            }
            if(recyclerView.adapter == adapterTasks)
            {
                val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
                startActivityForResult(intent, InsertCode)
            }
            if(recyclerView.adapter == adapterNotes)
            {
                val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
                startActivityForResult(intent, InsertCode)
            }

        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val weekMenu = findViewById<LinearLayout>(R.id.week_menu)
        bottomNavigation.setOnItemSelectedListener()
        {
            when(it.itemId)
            {
                R.id.plan -> {recyclerView.adapter = adapterPlan; EditCode = 8; InsertCode=1;weekMenu.visibility= View.VISIBLE}
                R.id.tasks-> {recyclerView.adapter = adapterTasks; EditCode = 13; InsertCode=6;weekMenu.visibility= View.INVISIBLE}
                R.id.notes-> {recyclerView.adapter = adapterNotes; EditCode = 14; InsertCode=7;weekMenu.visibility= View.INVISIBLE}
                else->{}
            }
            true
        }

        val monday = findViewById<Button>(R.id.monday)
        monday.setOnClickListener{
            recyclerView.adapter = adapterPlan
            InsertCode = 1
            EditCode = 8
        }
        val tuesday = findViewById<Button>(R.id.tuesday)
        tuesday.setOnClickListener{
            recyclerView.adapter = adapterPlanTuesday
            InsertCode = 2
            EditCode = 9
        }
        val wednesday = findViewById<Button>(R.id.wednesday)
        wednesday.setOnClickListener{
            recyclerView.adapter = adapterPlanWednesday
            InsertCode = 3
            EditCode = 10
        }
        val thursday = findViewById<Button>(R.id.thursday)
        thursday.setOnClickListener{
            recyclerView.adapter = adapterPlanThursday
            InsertCode = 4
            EditCode = 11
        }
        val friday = findViewById<Button>(R.id.friday)
        friday.setOnClickListener{
            recyclerView.adapter = adapterPlanFriday
            InsertCode = 5
            EditCode = 12
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)


        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            //intentData?.getStringExtra(NewGradeActivity.EXTRA_REPLY)?.let { reply ->
                gradeViewModel.insert(Monday(name,time,des))
            //}
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Tuesday(name,time,des))

        }
        if (requestCode == 3 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Wednesday(name,time,des))

        }
        if (requestCode == 4 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Thursday(name,time,des))

        }
        if (requestCode == 5 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Friday(name,time,des))

        }
        if (requestCode == 6 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Task(name,time,des))
        }
        if (requestCode == 7 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.insert(Note(name))

        }
        if (requestCode == 8 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateGradeByQuery(id,name, time, des)

        }
        if (requestCode == 9 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateTuesdayByQuery(id,name, time, des)

        }
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateWednesdayByQuery(id,name, time, des)

        }
        if (requestCode == 11 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateThursdayByQuery(id,name, time, des)

        }
        if (requestCode == 12 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateFridayByQuery(id,name, time, des)

        }
        if (requestCode == 13 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateTaskByQuery(id,name,time,des)

        }
        if (requestCode == 14 && resultCode == Activity.RESULT_OK) {
                gradeViewModel.updateNoteByQuery(id,name)

        }
    }
}

