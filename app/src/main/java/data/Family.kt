package com.example.rumily.data

data class Family(
    val _id: String,
    val name: String,
    val inviteCode: String,
    val createdBy: String, // The User ID of the creator
    val createdAt: String? = null,
    val updatedAt: String? = null
)

// We also need a model for the Join/Create response
data class FamilyResponse(
    val family: Family,
    val msg: String? = null
)