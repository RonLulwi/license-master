package com.ronlu.licensemaster.data.remote

import com.ronlu.licensemaster.data.CarResponse
import com.ronlu.licensemaster.data.VehicleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GovApi {

    @GET("datastore_search")
    suspend fun getCarData(
        @Query("resource_id") resourceId: String,
        @Query("filters") filters: String,
    ): Response<CarResponse<VehicleDto.CarD>>

    @GET("datastore_search")
    suspend fun getMotorcycleData(
        @Query("resource_id") resourceId: String,
        @Query("filters") filters: String,
    ): Response<CarResponse<VehicleDto.MotorcycleD>>
}