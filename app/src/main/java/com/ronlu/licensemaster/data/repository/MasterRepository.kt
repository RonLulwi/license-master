package com.ronlu.licensemaster.data.repository

import com.ronlu.licensemaster.data.local.MasterDatabase
import com.ronlu.licensemaster.data.remote.RetrofitInstance
import com.ronlu.licensemaster.domain.model.Items
import com.ronlu.licensemaster.utils.Constants.Companion.API_CAR_KEY
import com.ronlu.licensemaster.utils.Constants.Companion.API_MOTORCYCLE_KEY


class MasterRepository(
    private val database: MasterDatabase
) {

    //Retrofit - (Remote)
    suspend fun getCarData(filter: String) = RetrofitInstance.api.getCarData(API_CAR_KEY, filter)

    suspend fun getMotorcycleData(filter: String) = RetrofitInstance.api.getMotorcycleData(API_MOTORCYCLE_KEY, filter)

    //Room - (Local)
    suspend fun upsert(item: Items) = database.getMasterDao().upsert(item)

    fun getCarItems() = database.getMasterDao().getCarItems()

    fun getMotorcycleItems() = database.getMasterDao().getMotorcycleItems()

    suspend fun deleteItem(item: Items) = database.getMasterDao().deleteItem(item)

}