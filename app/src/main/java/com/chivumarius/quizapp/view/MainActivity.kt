package com.chivumarius.quizapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chivumarius.quizapp.R
import com.chivumarius.quizapp.databinding.ActivityMainBinding
import com.chivumarius.quizapp.model.Question
import com.chivumarius.quizapp.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // ♦ "Variable Declaration:"
    lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel: QuizViewModel
    lateinit var questionsList: List<Question>

    // ♦ The "Companion Object":
    companion object{
        // ♦ We "Store Variables":
        var result = 0
        var totalQuestions = 0
    }


    // ♦ The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ♦ Setting the "Content View":
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ♦ "Resetting" the "Scores Variables" for the "Quiz":
        result= 0
        totalQuestions = 0

        // ♦ "Getting" the "Response" from "Retrofit":
        quizViewModel = ViewModelProvider(this)
            .get(QuizViewModel::class.java)


        // ♦ "Displaying" the "First Question"
        //      → by Using "Coroutine":
        GlobalScope.launch (Dispatchers.Main){
            // ♦ "Observing":
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                // ♦ Checking: If there is a "Question" inside the "List":
                if (it.size > 0) {
                    questionsList = it
                    // ♦ Display "Log Message":
                    Log.i("TAGY","This the 1st question: ${questionsList[0]}")

                    // ♦ "Applying" the "binding" Keyword
                    //      → for "All Statements" inside "Curly Braces":
                    binding.apply {
                        // ♦ "Setting" the "text" Property
                        //      → for "Each Widget":
                        txtQuestion.text = "Question 1: "+ questionsList!![0].question
                        radio1.text =  questionsList!![0].option1
                        radio2.text =  questionsList!![0].option2
                        radio3.text =  questionsList!![0].option3
                        radio4.text =  questionsList!![0].option4
                    }
                }
            })}


        // ♦ Adding "Functionality" to "Next" Button
        var i = 1

        // ♦ "Applying" the "binding" Keyword
        //      → for "All Statements" inside "Curly Braces":
        binding.apply {
            // ♦ Setting "On Click Listener" for the "Next" Button:
            btnNext.setOnClickListener(View.OnClickListener {
                // ♦ Getting the "Checked Radio Button ID":
                val selectedOption =  radioGroup?.checkedRadioButtonId

                // ♦ Checking: If "Selected Option" is Not "Null" (ie "-1"):
                if (selectedOption != -1){
                    // ♦ Casting "selectedOption" as a "Radio Button":
                    val radbutton = findViewById<View>(selectedOption!!) as RadioButton

                    // ♦ We "Go" through "All" the "List Items" of "Questions"
                    //      → and "Store" Them in the "Form" of "Questions":
                    questionsList.let {
                        // ♦ "Checking":
                        if (i < it.size!!){
                            // ♦ Getting the "Number" of "Questions":
                            totalQuestions = it.size

                            // ♦ Checking: If the "Selected Radio Button" is "Correct":
                            if(radbutton.text.toString().equals(it[i-1].correct_option)){
                                // ♦ "Incrementing" the "Counter":
                                result++
                                // ♦ Displaying the "Number" of "Correct Answers":
                                txtResult?.text = "Correct Answer : $result"
                            }


                            // ♦ "Displaying" the "Next Questions" ♦
                            // ♦ Getting the "Question Number" from the "Question List":
                            txtQuestion.text = "Question ${i+1}: " + it[i].question

                            // ♦ "Upgrading" the "Radio Buttons"
                            //      → for "Each Option"
                            //      → in "Each Question":
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4


                            // ♦ "Checking": "If" it is the "Last Question":
                            if(i == it.size!!.minus(1)){
                                // ♦ Reset the "Next Button" as "Finish":
                                btnNext.text = "FINISH"
                            }

                            // ♦ Checking for the "radioGroup":
                            radioGroup?.clearCheck()
                            // ♦ "Incrementing" the "Number Question":
                            i++
                        } else {
                            // ♦ Checking: If the "Selected Radio Button" is "Correct":
                            if (radbutton.text.toString().equals(it[i-1].correct_option)){
                                // ♦ "Incrementing" the "Counter":
                                result++
                                // ♦ Displaying the "Number" of "Correct Answers":
                                txtResult?.text = "Correct Answer : $result"
                            } else {
                            }

                            // ♦ Changing/ Redirecting/ Forwarding "MainActivity" to "ResultActivity":
                            val intent  = Intent(this@MainActivity, ResultActivity::class.java )

                            // ♦ Starting "Activity" for "ResultActivity":
                            startActivity(intent)
                            // ♦ Finishing the "MainActivity":
                            finish()
                        }
                    }
                } else {
                    // ♦ If there is "No Selection Made" by the "User" ♦

                    // ♦ Displaying a "Toast Message":
                    Toast.makeText(this@MainActivity, " Please select One Option",
                        Toast.LENGTH_LONG).show()
                }
            })}
    }
}