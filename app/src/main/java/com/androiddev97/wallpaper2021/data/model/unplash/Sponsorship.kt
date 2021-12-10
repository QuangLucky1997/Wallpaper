package com.androiddev97.wallpaper2021.data.model.unplash

import java.util.*

data class Sponsorship(
    val impression_urls: List<Objects>, val tagline: String, val tagline_url: String,
    val sponsor:Sponsor
) {
}