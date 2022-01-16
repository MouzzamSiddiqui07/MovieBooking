package com.example.moviebooking.Model


import com.google.gson.annotations.SerializedName

data class PopularMoviesModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: MutableList<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)