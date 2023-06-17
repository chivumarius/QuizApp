package com.chivumarius.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chivumarius.quizapp.model.QuestionsList
import com.chivumarius.quizapp.repository.QuizRepository


// ♦ This will "Act" as the "ViewModel"
//      → by "Extending" the "ViewModel" Class:
class QuizViewModel : ViewModel() {
    // ♦ Connecting "ViewModel" with "Repository"
    //      → by Creating the "Instance":
    var repository : QuizRepository = QuizRepository()

    // ♦ "Variable Declaration":
     lateinit var questionsLiveData: LiveData<QuestionsList>

    // ♦ "Initializer":
    init {
        // ♦ "Storing" the "Result":
        questionsLiveData = repository.getQuestionsFromAPI()
    }


    // ♦ The "getQuestionsFromLiveData()" Method:
    fun getQuestionsFromLiveData():LiveData<QuestionsList>{
        return questionsLiveData
    }
}