package com.ronlu.licensemaster.data.mapper

import com.ronlu.licensemaster.data.VehicleDto
import com.ronlu.licensemaster.domain.model.Items

fun VehicleDto.CarD.toCar() : Items.Car {
    return Items.Car(
        plateNumber = plateNumber,
        color = color ?: "UnKnown",
        year = year ?: 0,
        title = title ?: "UnKnown",
        isPrivate = isPrivate ?: "UnKnown",
        manufacture = manufacture ?: "UnKnown",
        finishLevel = finishLevel ?: "UnKnown",
        fuelType = fuelType ?: "UnKnown"
    )
}

fun VehicleDto.MotorcycleD.toMotorcycle() : Items.Motorcycle {
    return Items.Motorcycle(
        plateNumber = plateNumber,
        color = color ?: "UnKnown",
        year = year ?: 0,
        title = title ?: "UnKnown",
        isPrivate = isPrivate ?: "UnKnown",
        manufacture = manufacture ?: "UnKnown",
        finishLevel = finishLevel ?: "UnKnown",
        fuelType = fuelType ?: "UnKnown",
        frontTire = frontTire ?: "UnKnown",
        rearTire = rearTire ?: "UnKnown"
    )
}