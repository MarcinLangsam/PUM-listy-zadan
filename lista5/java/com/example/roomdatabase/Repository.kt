package com.example.roomdatabase

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repository(private val gradeDao: GradeDao) {

    val allMonday: Flow<List<Monday>> = gradeDao.getGrades()
    val allTuesday: Flow<List<Tuesday>> = gradeDao.getTuesday()
    val allWednesday: Flow<List<Wednesday>> = gradeDao.getWednesday()
    val allThursday: Flow<List<Thursday>> = gradeDao.getThursday()
    val allFriday: Flow<List<Friday>> = gradeDao.getFriday()
    val allTask: Flow<List<Task>> = gradeDao.getTasks()
    val allNotes: Flow<List<Note>> = gradeDao.getNotes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(grade: Monday) {
        gradeDao.insert(grade)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(grade: Tuesday) {
        gradeDao.insert(grade)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(grade: Wednesday) {
        gradeDao.insert(grade)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(grade: Thursday) {
        gradeDao.insert(grade)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(grade: Friday) {
        gradeDao.insert(grade)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task) {
        gradeDao.insert(task)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: Note) {
        gradeDao.insert(note)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(grade: Monday) { gradeDao.delete(grade) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(grade: Tuesday) { gradeDao.delete(grade) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(grade: Wednesday) { gradeDao.delete(grade) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(grade: Thursday) { gradeDao.delete(grade) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(grade: Friday) { gradeDao.delete(grade) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(task: Task) { gradeDao.delete(task) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(note: Note) { gradeDao.delete(note) }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateGradeByQuery(id: Int, grade: String, time: String, description: String)
    { gradeDao.updateGradeByQuery(id,grade,time,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTuesdayByQuery(id: Int, grade: String, time: String, description: String)
    { gradeDao.updateTuesdayByQuery(id,grade,time,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateWednesdayByQuery(id: Int, grade: String, time: String, description: String)
    { gradeDao.updateWednesdayByQuery(id,grade,time,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateThursdayByQuery(id: Int, grade: String, time: String, description: String)
    { gradeDao.updateThursdayByQuery(id,grade,time,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateFridayByQuery(id: Int, grade: String, time: String, description: String)
    { gradeDao.updateFridayByQuery(id,grade,time,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTaskByQuery(id: Int, task: String, deadline: String, description: String)
    { gradeDao.updateTaskByQuery(id,task,deadline,description) }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateNoteByQuery(id: Int, note: String)
    { gradeDao.updateNoteByQuery(id,note) }

}