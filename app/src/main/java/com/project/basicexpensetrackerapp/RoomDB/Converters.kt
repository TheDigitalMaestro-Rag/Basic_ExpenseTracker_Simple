package com.project.basicexpensetrackerapp.RoomDB

import androidx.room.TypeConverter
import java.util.Date
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds


class Converters {
    // Convert Date to Long (epoch milliseconds) for Room storage
    @TypeConverter
    fun fromInstant(date: Date): Long? { // Use nullable types for flexibility
        return date.time
    }
    // Convert Long (epoch milliseconds) back to Date
    @TypeConverter
    fun toInstant(timestamp: Long?): Date? { // Use nullable types for flexibility
        return timestamp?.let { Date(it) }
    }
}