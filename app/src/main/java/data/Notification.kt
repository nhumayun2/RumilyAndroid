package com.example.rumily.data

data class Notification(
    val _id: String,
    val recipient: String,
    val sender: User?, // Populated in controller
    val type: String, // 'family_invite', 'new_need', 'new_message', etc.
    val content: String,
    val relatedId: String?, // ID of the related object (Need, Message, etc.)
    val isRead: Boolean = false,
    val createdAt: String? = null
)

// Response for "GET /notifications"
data class NotificationResponse(
    val notifications: List<Notification>
)