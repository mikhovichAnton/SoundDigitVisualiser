package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentInformationFirstBinding
import com.android.sounddigitvisualiser.domain.repository.local.navigation.FragmentNavigation
import com.android.sounddigitvisualiser.presentation.fragment.customanimation.VisualCustomViewFragment



class InformationFragmentFirst : Fragment() {

    private lateinit var binding: FragmentInformationFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToCustomAnimation(view)
        navigateToNextAnimation(view)
    }

    private fun navigateToCustomAnimation(view: View){
        with(binding){
            customAnimation.setOnClickListener {
                val action = InformationFragmentFirstDirections.
                actionInformationFragmentFirstToVisualCustomViewFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun navigateToNextAnimation(view: View){
        with(binding){
            nextAnimation.setOnClickListener {
                val action = InformationFragmentFirstDirections.
                actionInformationFragmentFirstToInformationFragmentSecond()
                view.findNavController().navigate(action)
            }
        }
    }
}