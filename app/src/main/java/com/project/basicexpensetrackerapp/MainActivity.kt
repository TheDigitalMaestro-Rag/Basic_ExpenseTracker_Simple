package com.project.basicexpensetrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.basicexpensetrackerapp.RoomDB.ExpenseViewModel.ExpenseViewModel
import com.project.basicexpensetrackerapp.RoomDB.ExpenseViewModel.ExpenseViewModelFactory
import com.project.basicexpensetrackerapp.RoomDB.MainApplication
import com.project.basicexpensetrackerapp.ui.theme.BasicExpenseTrackerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dao = MainApplication.amountDb.getAmountDao()
            val viewModel: ExpenseViewModel = viewModel(
                factory = ExpenseViewModelFactory(dao)
            )
            BasicExpenseTrackerAppTheme {
                ExpenseTrackerScreen(viewModel = viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpenseTrackerApp(){
//    val dao = MainApplication.amountDb.getAmountDao()
//    val viewModel: ExpenseViewModel = viewModel(
//        factory = ExpenseViewModelFactory(dao)
//    )
//    ExpenseTrackerScreen(viewModel = viewModel)
}