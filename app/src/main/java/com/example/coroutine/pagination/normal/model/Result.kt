package com.example.paginglibarary.model

data class Result(
    val _id: String,
    val author: String,
    val authorSlug: String,
    var content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)