//package com.project.expensetrackerapp.ExpenseRoomDB.MainApplication
//
//import android.app.Application
//import androidx.room.Room
//import com.project.expensetrackerapp.ExpenseRoomDB.DataBase.ExpenseDataBase
//
//class MainApplication: Application() {
//    companion object{
//        lateinit var dbanme:ExpenseDataBase
//            private set
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        dbanme = Room.databaseBuilder(
//            applicationContext,
//            ExpenseDataBase::class.java,
//            ExpenseDataBase.DBname
//        ).build()
//    }
//}