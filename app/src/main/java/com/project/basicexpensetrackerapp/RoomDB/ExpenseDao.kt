package com.project.basicexpensetrackerapp.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM ExpenseEntity ORDER BY createAt DESC")
    fun getAmount(): Flow<List<ExpenseEntity>> // Return a Flow of ExpenseEntity list

    @Insert
    suspend fun insertAmount(expense: ExpenseEntity) // Insert a single ExpenseEntity

    @Delete
    suspend fun deleteAmount(expense: ExpenseEntity) // Delete a single ExpenseEntity
}
