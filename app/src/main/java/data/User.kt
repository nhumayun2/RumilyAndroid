package com.example.rumily.data

import com.google.gson.annotations.SerializedName

// This matches your Mongoose "UserSchema"
data class User(
    // We map "_id" from Mongo to "id" in Kotlin for easier typing,
    // or keep it as _id. Let's keep it exact to avoid confusion.
    val _id: String,

    val name: String,
    val email: String,
    val phoneNumber: String,
    val role: String, // 'admin' or 'member'

    // These fields might be empty/null, so we use "?" (Nullable)
    val avatar: String? = null,
    val familyId: String? = null,
    val fcmToken: String? = null,
    val isVerified: Boolean = false
)

// We also need a class to handle the Login/Register response
// logic: res.json({ user: ..., token: ... })
data class AuthResponse(
    val user: User,
    val token: String,
    val msg: String? = null // For verification messages
)