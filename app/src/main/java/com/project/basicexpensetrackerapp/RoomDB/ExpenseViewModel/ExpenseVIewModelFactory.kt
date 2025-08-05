package com.project.basicexpensetrackerapp.RoomDB.ExpenseViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.basicexpensetrackerapp.RoomDB.ExpenseDao

class ExpenseViewModelFactory(private  val dao: ExpenseDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ExpenseViewModel(dao) as T
        }
        throw  IllegalArgumentException("UnKnown ViewModel Class")
    }

}