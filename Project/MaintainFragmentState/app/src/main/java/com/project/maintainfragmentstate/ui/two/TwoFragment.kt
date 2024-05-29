package com.project.maintainfragmentstate.ui.two

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.maintainfragmentstate.R
import com.project.maintainfragmentstate.databinding.FragmentTwoBinding
import com.project.maintainfragmentstate.ui.main.MainActivity

class TwoFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentTwoBinding.inflate(layoutInflater)

        return binding.root
    }

}