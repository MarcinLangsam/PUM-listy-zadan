package com.example.roomdatabase

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Entity(tableName = "monday_table")
class Monday(

    @ColumnInfo(name = "name") val grade: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
@Entity(tableName = "tuesday_table")
class Tuesday(

    @ColumnInfo(name = "name") val grade: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
@Entity(tableName = "wednesday_table")
class Wednesday(

    @ColumnInfo(name = "name") val grade: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
@Entity(tableName = "thursday_table")
class Thursday(

    @ColumnInfo(name = "name") val grade: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
@Entity(tableName = "friday_table")
class Friday(

    @ColumnInfo(name = "name") val grade: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

@Entity(tableName = "task_table")
class Task(

    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "deadline") val deadline: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

@Entity(tableName = "note_table")
class Note(

    @ColumnInfo(name = "note") val note: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

@Dao
interface GradeDao {

    @Query("SELECT * FROM monday_table ORDER BY time ASC")
    fun getGrades(): Flow<List<Monday>>
    @Query("SELECT * FROM tuesday_table ORDER BY time ASC")
    fun getTuesday(): Flow<List<Tuesday>>
    @Query("SELECT * FROM wednesday_table ORDER BY time ASC")
    fun getWednesday(): Flow<List<Wednesday>>
    @Query("SELECT * FROM thursday_table ORDER BY time ASC")
    fun getThursday(): Flow<List<Thursday>>
    @Query("SELECT * FROM friday_table ORDER BY time ASC")
    fun getFriday(): Flow<List<Friday>>
    @Query("SELECT * FROM task_table ORDER BY deadline ASC")
    fun getTasks(): Flow<List<Task>>
    @Query("SELECT * FROM note_table")
    fun getNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Monday)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Tuesday)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Wednesday)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Thursday)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Friday)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)


    @Query("DELETE FROM monday_table")
    suspend fun deleteallGrades()
    @Query("DELETE FROM task_table")
    suspend fun deleteallTasks()
    @Query("DELETE FROM note_table")
    suspend fun deleteallNotes()

    @Query("UPDATE monday_table SET name=:name, time=:time, description=:description WHERE id=:id")
    suspend fun updateGradeByQuery(id: Int, name: String, description: String, time: String)
    @Query("UPDATE tuesday_table SET name=:name, time=:time, description=:description WHERE id=:id")
    suspend fun updateTuesdayByQuery(id: Int, name: String, description: String, time: String)
    @Query("UPDATE wednesday_table SET name=:name, time=:time, description=:description WHERE id=:id")
    suspend fun updateWednesdayByQuery(id: Int, name: String, description: String, time: String)
    @Query("UPDATE thursday_table SET name=:name, time=:time, description=:description WHERE id=:id")
    suspend fun updateThursdayByQuery(id: Int, name: String, description: String, time: String)
    @Query("UPDATE friday_table SET name=:name, time=:time, description=:description WHERE id=:id")
    suspend fun updateFridayByQuery(id: Int, name: String, description: String, time: String)
    @Query("UPDATE task_table SET task=:task, description=:description, deadline=:deadline WHERE id=:id")
    suspend fun updateTaskByQuery(id: Int, task: String, description: String, deadline: String)
    @Query("UPDATE note_table SET note=:note WHERE id=:id")
    suspend fun updateNoteByQuery(id: Int, note: String)

    @Delete
    suspend fun delete(record: Monday)
    @Delete
    suspend fun delete(record: Tuesday)
    @Delete
    suspend fun delete(record: Wednesday)
    @Delete
    suspend fun delete(record: Thursday)
    @Delete
    suspend fun delete(record: Friday)
    @Delete
    suspend fun delete(task: Task)
    @Delete
    suspend fun delete(note: Note)

}

@Database(entities = [Monday::class, Tuesday::class, Wednesday::class, Thursday::class, Friday::class, Task::class, Note::class], version = 1, exportSchema = false)
abstract class GradeRoomDatabase : RoomDatabase() {

    abstract fun Dao(): GradeDao

    private class GradeDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: GradeRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): GradeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GradeRoomDatabase::class.java,
                    "grade_database"
                )
                    .addCallback(GradeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}