package com.test.at.model.data

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class DataModel(
    val title: String,
    val subTitle: String,
    val image: Int,
    val count: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}


@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, src: Int) {
    imageView.setImageResource(src)
}