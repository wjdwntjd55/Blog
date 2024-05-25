package com.project.maintainfragmentstate.ui.one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.maintainfragmentstate.R
import com.project.maintainfragmentstate.databinding.FragmentOneBinding
import com.project.maintainfragmentstate.ui.main.MainActivity

class OneFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentOneBinding.inflate(layoutInflater)

        return binding.root
    }


}