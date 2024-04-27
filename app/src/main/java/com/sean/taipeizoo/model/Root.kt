package com.sean.taipeizoo.model

data class JsonRoot<T>(
    val result: Result<T>
)

data class Result<T>(
    val results: List<T>
)