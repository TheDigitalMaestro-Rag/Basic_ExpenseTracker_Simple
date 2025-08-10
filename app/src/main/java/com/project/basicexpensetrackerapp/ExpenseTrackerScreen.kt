package com.project.expensetrackerapp

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.basicexpensetrackerapp.R
import com.project.expensetrackerapp.ExpenseRoomDB.Entity.ExpenseEntity
import com.project.expensetrackerapp.ViewModel.ExpenseViewModel
import kotlinx.coroutines.launch
import java.util.Date


@Composable
fun ExpenseTrackerScreen(viewModel: ExpenseViewModel) {
    val expenses by viewModel.allExpenses.collectAsState(initial = emptyList())

    val totalIncome = expenses.filter { it.category == "Income" }.sumOf { it.amount }
    val totalExpense = expenses.filter { it.category == "Expense" }.sumOf { it.amount }
    val totalSaved = totalIncome - totalExpense

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Income : ₹$totalIncome",
                color = Color(0xFF2E7D32),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Expense : ₹$totalExpense",
                color = Color(0xFFC62828),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Savings : ₹$totalSaved",
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
        ) {
            IconButton(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                onClick = {
                    // Add dummy income for testing
                    viewModel.insertExpense(
                        "Salary", 1000, "Income"
                    )
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
                    // Add dummy expense for testing
                    viewModel.insertExpense(
                        "Snacks", 200, "Expense"
                    )
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expense),
                    contentDescription = "Expense Button"
                )
            }
        }

        LazyColumn {
            items(expenses) { expense ->
                ExpenseCard(expense = expense, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ExpenseCard(viewModel: ExpenseViewModel, expense: ExpenseEntity) {
    val date = Date(expense.createdAt).toString()

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            viewModel.delteExpense(expense)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "₹${expense.amount} • ${expense.category} • $date")
        }
    }
}
