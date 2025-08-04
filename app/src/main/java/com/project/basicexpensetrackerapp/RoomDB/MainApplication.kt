package com.project.basicexpensetrackerapp.RoomDB

import android.app.Application
import androidx.room.Database
import androidx.room.Room


class MainApplication : Application() {
    companion object {
        lateinit var amountDb: ExpenseRoomDB
            private set
    }

    override fun onCreate() {
        super.onCreate()
        amountDb = Room.databaseBuilder(
            applicationContext,
            ExpenseRoomDB::class.java,
            ExpenseRoomDB.DBName
        ).build()
    }
}