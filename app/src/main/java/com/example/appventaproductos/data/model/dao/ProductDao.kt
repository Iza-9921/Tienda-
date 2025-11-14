/*package com.example.appventaproductos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appventaproductos.data.local.entity.AccesorioEntity
import com.example.appventaproductos.data.local.entity.CarriolaEntity
import com.example.appventaproductos.data.local.entity.RopaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RopaDao {
    @Query("SELECT * FROM ropa ORDER BY id ASC")
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

@Dao
interface CarriolaDao {
    @Query("SELECT * FROM carriola ORDER BY id ASC")
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

@Dao
interface AccesorioDao {
    @Query("SELECT * FROM accesorios ORDER BY id ASC")
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
*/