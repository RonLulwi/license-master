package com.ronlu.licensemaster.domain.model

import androidx.room.DatabaseView
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class Items {

    @Entity(tableName = "cars")
    data class Car(
        @PrimaryKey(autoGenerate = false)
        val plateNumber: Int,
        val color: String,
        val year: Int,
        val title: String,
        val isPrivate: String,
        val manufacture: String,
        val finishLevel: String,
        val fuelType: String

    ) : Items()

    @Entity(tableName = "motorcycles")
    data class Motorcycle(
        @PrimaryKey(autoGenerate = false)
        val plateNumber: Int,
        val color: String,
        val year: Int,
        val title: String,
        val isPrivate: String,
        val manufacture: String,
        val finishLevel: String,
        val fuelType: String,
        val frontTire: String,
        val rearTire: String
    ) : Items()

}