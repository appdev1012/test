package com.test.at.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.at.R
import com.test.at.model.data.DataModel
import com.test.at.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataRepository: DataRepository
): ViewModel() {

    private fun getAll(): List<DataModel> = dataRepository.getAll()

    private fun insert(dataModel: DataModel) = dataRepository.insert(dataModel)

    private fun delete(dataModel: DataModel) = dataRepository.delete(dataModel)

    private fun clear() = dataRepository.clear()

    fun getAll(context: Context): List<DataModel> {
        val currentData = getAll()
        if (currentData.isNotEmpty()) {
            clear()
        }
        val newData = fillData(context)
        newData.forEach(::insert)
        return getAll()
    }

    private fun fillData(context: Context): List<DataModel> {
        val randomIndices = (0 until 3).map { (0 until 8).random() }.distinct()
        return listOf(
            createDataModel(context, R.string.device_text, R.string.sub_text, R.drawable.ic_info_square, randomIndices.contains(0)),
            createDataModel(context, R.string.sensor_text, R.string.sub_text2, R.drawable.ic_smartphone, randomIndices.contains(1)),
            createDataModel(context, R.string.monitor_text, R.string.sub_text3, R.drawable.ic_object_scan, randomIndices.contains(2)),
            createDataModel(context, R.string.antivirus_text, R.string.sub_text4, R.drawable.ic_virus, randomIndices.contains(3)),
            createDataModel(context, R.string.memory_text, R.string.sub_text5, R.drawable.ic_library, randomIndices.contains(4)),
            createDataModel(context, R.string.manager_text, R.string.sub_text6, R.drawable.ic_file, randomIndices.contains(5)),
            createDataModel(context, R.string.battery_text, R.string.sub_text7, R.drawable.ic_battery, randomIndices.contains(6)),
            createDataModel(context, R.string.wifi_text, R.string.sub_text8, R.drawable.ic_router, randomIndices.contains(7))
        )
    }

    private fun createDataModel(context: Context, titleResId: Int, subtitleResId: Int, iconResId: Int, hasRandomValue: Boolean): DataModel {
        val value = if (hasRandomValue) (0..5).random() else 0
        return DataModel(context.getString(titleResId), context.getString(subtitleResId), iconResId, value)
    }

}