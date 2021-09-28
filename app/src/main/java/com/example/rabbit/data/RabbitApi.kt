package com.example.rabbit.data

import com.example.rabbit.data.model.Rabbit
import retrofit2.http.GET

interface RabbitApi {

    companion object{
        const val BASE_URL="http:/192.168.0.100:8080"
    }

    @GET("/random_rabbit")
    suspend fun getRandomRabbit():Rabbit
}