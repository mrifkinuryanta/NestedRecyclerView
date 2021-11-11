package com.dev.divig.nestedrecyclerview.model

data class MovieEntity(
    var id: Int,
    var title: String?,
    var overview: String?,
    var genres: String?,
    var releaseDate: String?,
    var runtime: Int,
    var posterPath: String?,
    var backdropPath: String?,
)
