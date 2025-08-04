package com.project.basicexpensetrackerapp.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ExpenseRoomDB : RoomDatabase() {
    companion object {
        const val DBName = "Expense_Tracker"
    }

    abstract fun getAmountDao(): ExpenseDao
}
