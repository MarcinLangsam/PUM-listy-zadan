package com.example.roomdatabase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWeekActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_week)
        var edit_grade_name = findViewById<EditText>(R.id.week_name)
        var edit_grade_time = findViewById<EditText>(R.id.week_time)
        var edit_grade_descripton = findViewById<EditText>(R.id.week_descrption)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_grade_name.text)) {

            }
            else {
                name = edit_grade_name.text.toString()
                time = edit_grade_time.text.toString()
                des = edit_grade_descripton.text.toString()
                replyIntent.putExtra("", name)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }
    }
}