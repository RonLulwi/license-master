package com.ronlu.licensemaster.data.mapper

import com.ronlu.licensemaster.data.VehicleDto
import com.ronlu.licensemaster.domain.model.Items

fun VehicleDto.CarD.toCar() : Items.Car {
    return Items.Car(
        plateNumber = plateNumber,
        color = color ?: "UnKnown",
        year = year ?: -1,
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
        year = year ?: -1,
        title = title ?: "UnKnown",
        isPrivate = isPrivate ?: "UnKnown",
        manufacture = manufacture ?: "UnKnown",
        finishLevel = finishLevel ?: "UnKnown",
        fuelType = fuelType ?: "UnKnown",
        frontTire = frontTire ?: "UnKnown",
        rearTire = rearTire ?: "UnKnown"
    )
}

fun VehicleDto.PublicD.toPublic() : Items.PublicVehicle {
    return Items.PublicVehicle(
        plateNumber = plateNumber,
        color = color ?: "UnKnown",
        year = year ?: -1,
        euLevel = euLevel ?: "UnKnown",
        vehicleType = vehicleType ?: "UnKnown",
        manufacture = manufacture ?: "UnKnown",
        numberOfSeats = numberOfSeats ?: "UnKnown",
        weight = weight ?: -1


    )
}