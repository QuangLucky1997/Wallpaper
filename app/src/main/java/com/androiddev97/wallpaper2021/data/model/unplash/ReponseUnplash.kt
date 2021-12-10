package com.androiddev97.wallpaper2021.data.model.unplash

import android.content.ClipDescription
import java.util.*

data class ReponseUnplash(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val promoted_at: String,
    val width: Long,
    val height: Long,
    val color: String,
    val blur_hash: String,
    val description: String,
    val alt: String,
    val url: Url,
    val linksL: Links,
    val listCategories: List<Objects>,
    val likes: Int,
    val liked_by_user: Boolean,
    val current_user_collections: List<Objects>,
    val sponsorship: Sponsorship,
    val topic_submissions: List<Objects>,
    val user: User
)