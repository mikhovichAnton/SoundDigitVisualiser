package com.android.sounddigitvisualiser.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.sounddigitvisualiser.data.model.AnimationImageEntity

@Database(entities = [AnimationImageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun parametersDao(): AnimationImageDao

    companion object {
        @Volatile
        private var INSATANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSATANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "parameters_database"
                ).build()
                instance.also { INSATANCE = it }
                instance
            }

        }
    }
}