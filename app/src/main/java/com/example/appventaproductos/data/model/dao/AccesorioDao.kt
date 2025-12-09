package com.example.appventaproductos.data.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appventaproductos.data.entity.AccesorioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccesorioDao {
    @Query("SELECT * FROM accesorios")
    fun getAll(): Flow<List<AccesorioEntity>>

    @Query("SELECT * FROM accesorios WHERE id = :id")
    fun getById(id: Int): Flow<AccesorioEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: AccesorioEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<AccesorioEntity>)

    @Update
    suspend fun update(item: AccesorioEntity)

    @Query("DELETE FROM accesorios WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM accesorios")
    suspend fun count(): Int
}
