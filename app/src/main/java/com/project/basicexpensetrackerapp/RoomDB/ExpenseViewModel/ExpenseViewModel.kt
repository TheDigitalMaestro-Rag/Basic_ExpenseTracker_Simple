package com.project.basicexpensetrackerapp.RoomDB.ExpenseViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.basicexpensetrackerapp.RoomDB.ExpenseDao
import com.project.basicexpensetrackerapp.RoomDB.ExpenseEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class ExpenseViewModel(private val dao: ExpenseDao) : ViewModel() {
    private val _expenses = dao.getAmount()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), emptyList()
        )

    val expenses: StateFlow<List<ExpenseEntity>> = _expenses

    val totalIncome: StateFlow<Int> = _expenses
        .map { list -> list.filter { it.amount >0 }.sumOf { it.amount } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), 0
        )

    val totalExpense: StateFlow<Int> = _expenses
        .map { list -> list.filter { it.amount > 0 }.sumOf { it.amount } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), 0
        )

    val totalSaved: StateFlow<Int> = _expenses
        .map { list ->
            val income = list.filter { it.amount > 0}.sumOf { it.amount }
            val expense = list.filter { it.amount < 0}.sumOf { it.amount }
            income + expense
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), 0
        )

    fun addIncome(amount: Int){
        viewModelScope.launch {
            dao.insertAmount(ExpenseEntity(amount = amount, createAt = Date()))
        }
    }

    fun addExpense(amount: Int){
        viewModelScope.launch {
            dao.insertAmount(ExpenseEntity(amount = -amount, createAt = Date()))
        }
    }

    fun deleteExpense(expense: ExpenseEntity){
        viewModelScope.launch {
            dao.deleteAmount(expense)
        }
    }
}