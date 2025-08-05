package com.project.basicexpensetrackerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.basicexpensetrackerapp.RoomDB.ExpenseEntity
import com.project.basicexpensetrackerapp.RoomDB.ExpenseViewModel.ExpenseViewModel
import com.project.basicexpensetrackerapp.RoomDB.MainApplication
import kotlinx.coroutines.launch
import java.util.Date


@Composable fun ExpenseTrackerScreen(viewModel: ExpenseViewModel) {

    val scope = rememberCoroutineScope()

    val expenses by viewModel.expenses.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()
    val totalSaved by viewModel.totalSaved.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Text(
                text = "Income : $totalIncome",
                color = Color(0xFF2E7D32),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Expense : ${-totalExpense}",
                color = Color(0xFFC62828),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Savings : $totalSaved",
                color = Color(0xFF1565C0),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(color = Color.White),
            Arrangement.SpaceBetween
        ){
            IconButton(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    scope.launch {
                        // Example: Add an income entry
                        val newExpense = ExpenseEntity(amount = 500, createAt = Date())
                        MainApplication.amountDb.getAmountDao().insertAmount(newExpense)
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_income),
                    contentDescription = "Income Button"
                )
            }

            IconButton(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.secondaryContainer),
                onClick = {
                    scope.launch {
                        // Example: Add an expense entry
                        val newExpense = ExpenseEntity(amount = -100, createAt = Date()) // Negative for expense
                        MainApplication.amountDb.getAmountDao().insertAmount(newExpense)
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expense),
                    contentDescription = "Expense Button"
                )
            }
        }
        LazyColumn {
            items(expenses){ expense -> // Use the expenses from the database
                ExpenseCard(expense)
            }
        }
    }
}

@Composable
fun ExpenseCard(expense: ExpenseEntity) { // Changed parameter to ExpenseEntity
    val scope = rememberCoroutineScope()
    Card (
        modifier = Modifier.padding(8.dp),
        onClick = {
            scope.launch {
                MainApplication.amountDb.getAmountDao().deleteAmount(expense = expense)
            }
        }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(25.dp),
        ){
            Text(text = "Amount: ${expense.amount} on ${expense.createAt}") // Display amount and date
        }
    }
}