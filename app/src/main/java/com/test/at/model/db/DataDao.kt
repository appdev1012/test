package com.test.at.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.at.model.data.DataModel

@Dao
interface DataDao {

    @Query("SELECT * FROM topics")
    fun getAll(): List<DataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataModel: DataModel)

    @Delete
    fun delete(dataModel: DataModel)

    @Query("DELETE FROM topics")
    fun clear()
}