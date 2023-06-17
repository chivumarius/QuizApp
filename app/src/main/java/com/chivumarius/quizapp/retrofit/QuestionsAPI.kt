package com.chivumarius.quizapp.retrofit

import com.chivumarius.quizapp.model.QuestionsList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsAPI {
    // ♦ Getting "Questions API":
    @GET("questionsapi.php")
    // ♦ Using "Coroutine":
    suspend fun getQuestions(): Response<QuestionsList>
}