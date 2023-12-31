package com.ronlu.licensemaster.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ronlu.licensemaster.domain.model.Items

@Dao
interface MasterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCar(car: Items.Car): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMotorcycle(motorcycle: Items.Motorcycle): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPublic(publicVehicle: Items.PublicVehicle): Long


    //(Check if I can send the Table Name as a parameter of the func -> then in the repository/viewModel do the casting)
    @Query("SELECT * FROM publicVehicle")
    fun getPublicItems(): LiveData<List<Items.PublicVehicle>>

    @Query("SELECT * FROM cars")
    fun getCarItems(): LiveData<List<Items.Car>>

    @Query("SELECT * FROM motorcycles")
    fun getMotorcycleItems(): LiveData<List<Items.Motorcycle>>



    @Delete
    suspend fun deleteCar(car: Items.Car)

    @Delete
    suspend fun deleteMotorcycle(motorcycle: Items.Motorcycle)

    @Delete
    suspend fun deletePublic(publicVehicle: Items.PublicVehicle)


    //(deleteItem(item : Items) -> Check if this works for all types of Item)
}