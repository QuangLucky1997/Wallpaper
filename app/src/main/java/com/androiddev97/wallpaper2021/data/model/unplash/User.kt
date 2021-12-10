package com.androiddev97.wallpaper2021.data.model.unplash

data class User(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    val first_name: String,
    val last_name: String,
    val twitter_username: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val links: LinksUser,
    val profile_image: ProfileImageUser,
    val instagram_username: String,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val accepted_tos: Boolean,
    val for_hire: Boolean,
    val social: SocialUser
)
