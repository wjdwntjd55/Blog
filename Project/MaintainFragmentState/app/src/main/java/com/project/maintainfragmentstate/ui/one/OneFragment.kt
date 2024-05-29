package com.project.maintainfragmentstate.ui.one

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.maintainfragmentstate.R
import com.project.maintainfragmentstate.databinding.FragmentOneBinding
import com.project.maintainfragmentstate.ui.main.MainActivity
import com.project.maintainfragmentstate.ui.main.MainViewModel

class OneFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOneBinding

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: OneViewModel

    val TAG = "OneFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentOneBinding.inflate(layoutInflater)

        // MainViewModel을 Activity 범위에서 가져오기
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel = ViewModelProvider(this)[OneViewModel::class.java]
        viewModel.getAllData()

        observeData()
        swipeRefresh()

        return binding.root
    }

    private fun observeData() {

        mainViewModel.oneState.observe(viewLifecycleOwner) { state ->
            if (state) {
                binding.recyclerViewOne.smoothScrollToPosition(0)
            }
        }

        viewModel.result.observe(viewLifecycleOwner) { dataList ->
            Log.d(TAG, "it : $dataList")

            binding.recyclerViewOne.run {
                adapter = OneAdapter(dataList)
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayoutOne.setOnRefreshListener {
            viewModel.getAllData()
            // 새로고침 아이콘 없애기
            binding.swipeRefreshLayoutOne.isRefreshing = false
        }
    }

}