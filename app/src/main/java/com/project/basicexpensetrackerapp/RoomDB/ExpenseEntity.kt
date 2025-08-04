package com.project.basicexpensetrackerapp.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Int,
    val createAt: Date
)
