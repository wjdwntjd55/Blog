package com.project.fragmentanimation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.fragmentanimation.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentOneBinding.inflate(layoutInflater)

        binding.recyclerViewOne.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OneAdapter(mainDataList) { data ->
                val bundle = Bundle().apply {
                    putInt("imageResId", data.imageResId)
                    putString("name", data.name)
                }
                mainActivity.addFragment(MainActivity.TWO_FRAGMENT, true, bundle)
            }
        }

        return binding.root
    }

}