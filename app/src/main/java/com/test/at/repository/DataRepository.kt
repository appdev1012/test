package com.test.at.repository

import com.test.at.model.db.DataDao
import com.test.at.model.data.DataModel
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val dataDao: DataDao
){

    fun getAll(): List<DataModel> {
        return dataDao.getAll()
    }

    fun insert(dataModel: DataModel) {
        dataDao.insert(dataModel)
    }

    fun delete(dataModel: DataModel) {
        dataDao.delete(dataModel)
    }

    fun clear(){
        dataDao.clear()
    }
}