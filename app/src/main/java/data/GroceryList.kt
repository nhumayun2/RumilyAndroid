package com.example.rumily.data

data class GroceryItem(
    val _id: String,
    val name: String,
    val isPurchased: Boolean = false,
    val purchasedBy: User? = null // Can be null if nobody bought it yet
)

data class GroceryList(
    val _id: String,
    val title: String,
    val familyId: String,
    val createdBy: String,
    val items: List<GroceryItem>,
    val seenBy: List<User>, // List of users who viewed this
    val createdAt: String? = null
)

// Response for "GET /grocery"
data class GroceryListResponse(
    val lists: List<GroceryList>
)

// Response for "POST /create" or adding items
data class SingleGroceryResponse(
    val list: GroceryList
)