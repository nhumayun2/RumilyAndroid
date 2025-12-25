package com.example.rumily.data

data class Need(
    val _id: String,
    val content: String,
    val status: String, // 'active', 'fulfilled'
    val urgency: String, // 'normal', 'urgent'
    val createdBy: User, // Backend populates this with user details
    val familyId: String,
    val createdAt: String? = null
)

// Response for "GET /needs"
data class NeedResponse(
    val needs: List<Need>,
    val count: Int
)

// Response for "POST /needs/create" or "PATCH"
data class SingleNeedResponse(
    val need: Need
)