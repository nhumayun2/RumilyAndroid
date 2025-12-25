package com.example.rumily.data

data class Message(
    val _id: String,
    val content: String? = null, // Can be null if only sending a file
    val attachment: String? = null, // Cloudinary URL
    val fileType: String = "text", // 'image', 'video', 'text'
    val sender: User, // Because you populate 'name' and 'avatar' in the controller
    val familyId: String,
    val createdAt: String? = null
)

// Response for "GET /history"
data class ChatHistoryResponse(
    val messages: List<Message>,
    val count: Int
)

// Response for "POST /send"
data class SendMessageResponse(
    val message: Message
)