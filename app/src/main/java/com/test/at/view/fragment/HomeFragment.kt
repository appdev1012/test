package com.test.at.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.test.at.R
import com.test.at.view.adapter.DataAdapter
import com.test.at.utils.changeLayersColor
import com.test.at.databinding.FragmentHomeBinding
import com.test.at.model.data.DataModel
import com.test.at.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val homeVewModel by viewModels<HomeViewModel>()

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val topicAdapter: DataAdapter by lazy { DataAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottieView.changeLayersColor(R.color.blue_D6)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
        val screenHeight = resources.displayMetrics.heightPixels
        val peekHeight = (screenHeight * 0.38f).toInt()
        val maxHeight = (screenHeight * 0.95f).toInt()
        bottomSheetBehavior.peekHeight = peekHeight
        binding.bottomSheet.layoutParams.height = maxHeight

        binding.rvTopic.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            hasFixedSize()
            adapter = topicAdapter
        }

        val data = homeVewModel.getAll(requireContext())

        calculateProblems(data)

        topicAdapter.setList(data)

    }

    private fun calculateProblems(data: List<DataModel>) {
        var problems = 0
        data.forEach {
            if (it.count > 0) {
                problems += it.count
            }
        }
        binding.problemCount.text = String.format(getString(R.string.problems_text), problems.toString())
    }
}