package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewTaskActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_task)
        var task = findViewById<EditText>(R.id.task_name)
        var deadline = findViewById<EditText>(R.id.task_deadline)
        var description = findViewById<EditText>(R.id.task_description)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(task.text)) {

            }
            else {
                name = task.text.toString()
                time = deadline.text.toString()
                des = description.text.toString()
                replyIntent.putExtra("", name)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }

    //companion object {
    //    const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    //}
}