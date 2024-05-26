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

class OneFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOneBinding

    private lateinit var viewModel: OneViewModel

    val TAG = "OneFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentOneBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[OneViewModel::class.java]
        viewModel.getAllData()

        observeData()

        return binding.root
    }

    private fun observeData() {
        viewModel.result.observe(viewLifecycleOwner) { dataList ->
            Log.d(TAG, "it : $dataList")

            binding.recyclerViewOne.run {
                adapter = OneAdapter(dataList)
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

}