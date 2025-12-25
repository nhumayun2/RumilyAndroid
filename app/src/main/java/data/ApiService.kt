package com.example.rumily.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ApiService {

    // --- AUTH ---
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: Map<String, String>): AuthResponse

    @POST("api/v1/auth/register")
    suspend fun register(@Body request: Map<String, String>): Map<String, String> // Returns msg & email

    @POST("api/v1/auth/verify-email")
    suspend fun verifyEmail(@Body request: Map<String, String>): AuthResponse

    // --- FAMILY ---
    @POST("api/v1/family/create")
    suspend fun createFamily(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): FamilyResponse

    @POST("api/v1/family/join")
    suspend fun joinFamily(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): FamilyResponse

    // --- CHAT ---
    @GET("api/v1/chat/history")
    suspend fun getChatHistory(
        @Header("Authorization") token: String
    ): ChatHistoryResponse

    // --- NEEDS ---
    @GET("api/v1/needs")
    suspend fun getNeeds(
        @Header("Authorization") token: String
    ): NeedResponse

    @POST("api/v1/needs/create")
    suspend fun createNeed(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): SingleNeedResponse

    // --- GROCERY ---
    @GET("api/v1/grocery")
    suspend fun getGroceries(
        @Header("Authorization") token: String
    ): GroceryListResponse

    @POST("api/v1/grocery/create")
    suspend fun createGroceryList(
        @Header("Authorization") token: String,
        @Body request: Map<String, String>
    ): SingleGroceryResponse
}