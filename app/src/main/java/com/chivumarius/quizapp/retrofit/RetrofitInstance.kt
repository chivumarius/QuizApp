package com.chivumarius.quizapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    // ♦ Storing the "baseUrl"
    //      → from "XAMPP/htdocs/quiz/":
    val baseUrl  = "http://192.168.0.127/quiz/"

    // ♦ The "getRetrofitInstance()" Method
    //      → in which we Create an "Instance" from the "Retrofit"
    //      → and make the "Connection" with the "XAMPP" Local Host:
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}