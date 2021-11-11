package com.dev.divig.nestedrecyclerview.model

data class ParentEntity(
    var parentItemTitle: String,
    var childItemList: List<MovieEntity>
)