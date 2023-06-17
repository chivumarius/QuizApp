package com.chivumarius.quizapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chivumarius.quizapp.model.QuestionsList
import com.chivumarius.quizapp.retrofit.QuestionsAPI
import com.chivumarius.quizapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class QuizRepository {

    // ♦ Variable Declaration:
    var questionsAPI: QuestionsAPI = RetrofitInstance().getRetrofitInstance()
        .create(QuestionsAPI::class.java)


    // ♦ The "getQuestionsFromAPI()" Method:
    fun getQuestionsFromAPI():LiveData<QuestionsList>{
        // ♦ "Live Data":
        var data = MutableLiveData<QuestionsList>()

        // ♦ Variable Declaration:
        var questionsList : QuestionsList

        // ♦ gGtting the "Response" in the "Background":
        GlobalScope.launch(Dispatchers.IO) {
            // ♦ Returning the "Response<QuestionsList>":
            val response = questionsAPI.getQuestions()

            // ♦ Checking: If "There Is" an "Response":
            if (response != null) {
                // ♦ "Saving" the "Data" to "List":
                questionsList = response.body()!!

                // ♦ Calling the "postValue()" (because we Use "Coroutines"):
                data.postValue(questionsList)
                // ♦ Displaying in "Terminal":
                Log.i("TAGY", "" + data.value)
            }
        }
        return data
    }
}