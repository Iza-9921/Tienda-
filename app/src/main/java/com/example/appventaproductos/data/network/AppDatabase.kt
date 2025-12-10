package com.example.appventaproductos.data.network


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appventaproductos.data.model.dao.AccesorioDao
import com.example.appventaproductos.data.model.dao.CarriolaDao
import com.example.appventaproductos.data.model.dao.RopaDao
import com.example.appventaproductos.data.entity.AccesorioEntity
import com.example.appventaproductos.data.entity.CarriolaEntity
import com.example.appventaproductos.data.entity.RopaEntity

@Database(
    entities = [RopaEntity::class, CarriolaEntity::class, AccesorioEntity::class],
    version = 2, // <-- El cambio clave está aquí
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
            ).fallbackToDestructiveMigration().build()
        }
    }
}

