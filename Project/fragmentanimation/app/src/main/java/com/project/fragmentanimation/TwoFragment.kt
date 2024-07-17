package com.project.fragmentanimation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.fragmentanimation.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentTwoBinding.inflate(layoutInflater)

        val imageResId = arguments?.getInt("imageResId")
        val name = arguments?.getString("name")

        binding.run {

            toolbarTwo.run {
                title = name

                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.TWO_FRAGMENT)
                }
            }

            if (imageResId != null) {
                imageViewTow.setImageResource(imageResId)
            }
        }

        return binding.root
    }

}