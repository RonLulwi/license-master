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
    suspend fun upsert(item: Items) : Long

    @Query("SELECT * FROM cars")
    fun getCarItems() : LiveData<List<Items.Car>>

    @Query("SELECT * FROM motorcycles")
    fun getMotorcycleItems() : LiveData<List<Items.Motorcycle>>

    @Delete
    suspend fun deleteItem(item: Items)
}