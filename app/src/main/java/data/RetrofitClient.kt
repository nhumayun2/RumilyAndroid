package com.example.rumily.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// "object" in Kotlin means this is a Singleton.
// It means we only ever create ONE connection to the server,
// and reuse it everywhere in the app.
object RetrofitClient {

    // 10.0.2.2 is the Emulator's way of reaching your PC's localhost
    // TODO: Make sure your Express server is running on port 8001!
    // If it is 3000, change this to 3000.
    private const val BASE_URL = "http://10.0.2.2:8001/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson to parse JSON
            .build()

        retrofit.create(ApiService::class.java)
    }
}