package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GradeViewModel(private val repository: Repository) : ViewModel() {

    val allMonday: LiveData<List<Monday>> = repository.allMonday.asLiveData()
    val allTuesday: LiveData<List<Tuesday>> = repository.allTuesday.asLiveData()
    val allWednesday: LiveData<List<Wednesday>> = repository.allWednesday.asLiveData()
    val allThursday: LiveData<List<Thursday>> = repository.allThursday.asLiveData()
    val allFriday: LiveData<List<Friday>> = repository.allFriday.asLiveData()
    val allTask: LiveData<List<Task>> = repository.allTask.asLiveData()
    val allNotes: LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun insert(record: Monday) = viewModelScope.launch {
        repository.insert(record)
    }
    fun insert(record: Tuesday) = viewModelScope.launch {
        repository.insert(record)
    }
    fun insert(record: Wednesday) = viewModelScope.launch {
        repository.insert(record)
    }
    fun insert(record: Thursday) = viewModelScope.launch {
        repository.insert(record)
    }
    fun insert(record: Friday) = viewModelScope.launch {
        repository.insert(record)
    }
    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun delete(record: Monday) = viewModelScope.launch {
        repository.delete(record)
    }
    fun delete(record: Tuesday) = viewModelScope.launch {
        repository.delete(record)
    }
    fun delete(record: Wednesday) = viewModelScope.launch {
        repository.delete(record)
    }
    fun delete(record: Thursday) = viewModelScope.launch {
        repository.delete(record)
    }
    fun delete(record: Friday) = viewModelScope.launch {
        repository.delete(record)
    }
    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun updateGradeByQuery(id: Int, name: String, time: String, description: String) = viewModelScope.launch{
        repository.updateGradeByQuery(id, name,time,description)
    }
    fun updateTuesdayByQuery(id: Int, name: String, time: String, description: String) = viewModelScope.launch{
        repository.updateTuesdayByQuery(id, name,time,description)
    }
    fun updateWednesdayByQuery(id: Int, name: String, time: String, description: String) = viewModelScope.launch{
        repository.updateWednesdayByQuery(id, name,time,description)
    }
    fun updateThursdayByQuery(id: Int, name: String, time: String, description: String) = viewModelScope.launch{
        repository.updateThursdayByQuery(id, name,time,description)
    }
    fun updateFridayByQuery(id: Int, name: String, time: String, description: String) = viewModelScope.launch{
        repository.updateFridayByQuery(id, name,time,description)
    }
    fun updateTaskByQuery(id: Int, task: String, deadline: String, description: String) = viewModelScope.launch{
        repository.updateTaskByQuery(id, task,deadline,description)
    }
    fun updateNoteByQuery(id: Int, note: String) = viewModelScope.launch{
        repository.updateNoteByQuery(id, note)
    }

}

class GradeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GradeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}