package com.project.basicexpensetrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.basicexpensetrackerapp.ui.theme.BasicExpenseTrackerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicExpenseTrackerAppTheme {
                ExpenseTrackerScreen()
            }
        }
    }
}

