package com.ronlu.licensemaster.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ronlu.licensemaster.domain.model.Items

@Database(
    entities = [Items.Car::class, Items.Motorcycle::class],
    version = 1
)
abstract class MasterDatabase : RoomDatabase(){
    abstract fun getMasterDao() : MasterDao

    companion object {
        @Volatile
        private var instance : MasterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MasterDatabase::class.java,
                "cars_db.db"
            ).build()
    }



}