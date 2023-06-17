package com.chivumarius.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.chivumarius.quizapp.R
import com.chivumarius.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    // ♦ Using "Data binding":
    lateinit var binding: ActivityResultBinding

    // ♦ The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ♦ Setting "Content View" for this "Screen":
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)

        // ♦ Displaying the "Score" for the "User":
        binding.txtAnswer.text = "Your Score is: " + MainActivity.result +"/"+MainActivity.totalQuestions

        // ♦ Setting "On Click Listener"  for "Back" Button:
        binding.btnBack.setOnClickListener(){
            // ♦ Changing/ Redirecting/ Forwarding "ResultActivity" to "MainActivity":
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            // ♦ Starting "Activity" for "MainActivity":
            startActivity(intent)
        }
    }
}