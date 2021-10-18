package com.example.postrequestapprevisited

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser(): Call<List<Users.UsersInfo>>


    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: Users.UsersInfo): Call<Users.UsersInfo>

    @Headers("Content-Type: application/json")
    @POST("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: Users.UsersInfo): Call<Users.UsersInfo>

    @Headers("Content-Type: application/json")
    @POST("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

}