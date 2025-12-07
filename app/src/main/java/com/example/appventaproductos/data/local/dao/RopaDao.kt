package com.example.appventaproductos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appventaproductos.data.local.entity.RopaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RopaDao {
    @Query("SELECT * FROM ropa")
    fun getAll(): Flow<List<RopaEntity>>

    @Query("SELECT * FROM ropa WHERE id = :id")
    fun getById(id: Int): Flow<RopaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: RopaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<RopaEntity>)

    @Update
    suspend fun update(item: RopaEntity)

    @Query("DELETE FROM ropa WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM ropa")
    suspend fun count(): Int
}
