package com.example.appventaproductos.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appventaproductos.data.entity.CarriolaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarriolaDao {
    @Query("SELECT * FROM carriola")
    fun getAll(): Flow<List<CarriolaEntity>>

    @Query("SELECT * FROM carriola WHERE id = :id")
    fun getById(id: Int): Flow<CarriolaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CarriolaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CarriolaEntity>)

    @Update
    suspend fun update(item: CarriolaEntity)

    @Query("DELETE FROM carriola WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM carriola")
    suspend fun count(): Int
}
