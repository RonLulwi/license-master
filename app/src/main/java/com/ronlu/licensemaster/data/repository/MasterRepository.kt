package com.ronlu.licensemaster.data.repository

import com.ronlu.licensemaster.data.local.MasterDatabase
import com.ronlu.licensemaster.data.remote.RetrofitInstance
import com.ronlu.licensemaster.domain.model.Items
import com.ronlu.licensemaster.utils.Constants.Companion.API_CAR_KEY
import com.ronlu.licensemaster.utils.Constants.Companion.API_MOTORCYCLE_KEY
import com.ronlu.licensemaster.utils.Constants.Companion.API_PUBLIC_KEY


class MasterRepository(
    private val database: MasterDatabase
) {

    //Retrofit - (Remote)
    suspend fun getCarData(filter: String) = RetrofitInstance.api.getCarData(API_CAR_KEY, filter)

    suspend fun getMotorcycleData(filter: String) = RetrofitInstance.api.getMotorcycleData(API_MOTORCYCLE_KEY, filter)

    suspend fun getPublicData(filter: String) = RetrofitInstance.api.getPublicData(API_PUBLIC_KEY, filter)

    //Room - (Local)
    suspend fun upsertCar(item: Items.Car) = database.getMasterDao().upsertCar(item)
    suspend fun upsertMotorcycle(item: Items.Motorcycle) = database.getMasterDao().upsertMotorcycle(item)
    suspend fun upsertPublic(item: Items.PublicVehicle) = database.getMasterDao().upsertPublic(item)

    fun getCarItems() = database.getMasterDao().getCarItems()

    fun getMotorcycleItems() = database.getMasterDao().getMotorcycleItems()

    fun getPublicItems() = database.getMasterDao().getPublicItems()

    suspend fun deleteItem(item: Items) =
        when(item){
            is Items.Car -> database.getMasterDao().deleteCar(item)
            is Items.Motorcycle -> database.getMasterDao().deleteMotorcycle(item)
            is Items.PublicVehicle -> database.getMasterDao().deletePublic(item)
        }

}