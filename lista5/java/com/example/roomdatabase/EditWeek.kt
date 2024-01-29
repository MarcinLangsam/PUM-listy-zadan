package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText


class EditWeek : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_week)
        var editName = findViewById<EditText>(R.id.week_name)
        var editTime = findViewById<EditText>(R.id.week_time)
        var editDescription = findViewById<EditText>(R.id.week_descrption)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editName.text)) {

            }
            else {
                name = editName.text.toString()
                time = editTime.text.toString()
                des = editDescription.text.toString()
                replyIntent.putExtra("", name)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}