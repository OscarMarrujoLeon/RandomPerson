package com.omarrujo.randomperson.model

import com.omarrujo.randomperson.repository.UserService
import com.omarrujo.randomperson.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(UserService::class.java)

}