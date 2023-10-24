package com.omarrujo.randomperson.repository


import com.omarrujo.randomperson.model.UserData
import com.omarrujo.randomperson.util.Constants
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET(Constants.ONE_CALL)
    fun getRandomUser(): Call<UserData>
}