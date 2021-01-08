package com.accubits.test.moviesapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.accubits.test.moviesapp.model.data.Movie

/**
 *  @author Rolbin, created on 08-01-2021.
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}