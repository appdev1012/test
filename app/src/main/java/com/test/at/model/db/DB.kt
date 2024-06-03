package com.test.at.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.at.model.data.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun dataDao(): DataDao
}