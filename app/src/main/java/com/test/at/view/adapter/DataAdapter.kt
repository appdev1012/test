package com.test.at.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.at.databinding.ItemRowProblemTopicBinding
import com.test.at.databinding.ItemRowTopicBinding
import com.test.at.model.data.DataModel


class DataAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList: List<DataModel> = listOf()

    companion object {
        const val VIEW_TYPE_SIMPLE = 0
        const val VIEW_TYPE_PROBLEM = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DataModel>) {
        dataList = list
        notifyDataSetChanged()
    }

    class TopicHolder(val binding: ItemRowTopicBinding): RecyclerView.ViewHolder(binding.root)

    class ProblemTopicHolder(val binding: ItemRowProblemTopicBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SIMPLE -> TopicHolder(ItemRowTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            VIEW_TYPE_PROBLEM -> ProblemTopicHolder(ItemRowProblemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TopicHolder -> {
                val topic = dataList[position]
                holder.binding.topic = topic
            }
            is ProblemTopicHolder -> {
                val topic = dataList[position]
                holder.binding.topic = topic
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position].count > 0) VIEW_TYPE_PROBLEM else VIEW_TYPE_SIMPLE
    }

}