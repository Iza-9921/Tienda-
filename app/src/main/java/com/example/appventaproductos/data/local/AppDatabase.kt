package com.example.appventaproductos.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appventaproductos.data.local.dao.AccesorioDao
import com.example.appventaproductos.data.local.dao.CarriolaDao
import com.example.appventaproductos.data.local.dao.RopaDao
import com.example.appventaproductos.data.local.entity.AccesorioEntity
import com.example.appventaproductos.data.local.entity.CarriolaEntity
import com.example.appventaproductos.data.local.entity.RopaEntity

@Database(
    entities = [RopaEntity::class, CarriolaEntity::class, AccesorioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ropaDao(): RopaDao
    abstract fun carriolaDao(): CarriolaDao
    abstract fun accesorioDao(): AccesorioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "tienda_bebes.db"
            ).build()
        }
    }
}

