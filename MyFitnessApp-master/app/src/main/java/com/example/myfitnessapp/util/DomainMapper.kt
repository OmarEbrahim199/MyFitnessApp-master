package com.example.myfitnessapp.util

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T): DomainModel
}