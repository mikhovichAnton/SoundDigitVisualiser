package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentInformationThirdBinding


class InformationFragmentThird : Fragment() {
    private lateinit var binding: FragmentInformationThirdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationThirdBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideNavigationBar()
        navigateToAIAnimation(view)
        navigateToPreviousAnimation(view)
    }

    private fun navigateToPreviousAnimation(view: View) {
        with(binding) {
            toBackAnimation.setOnClickListener {
                val action =
                    InformationFragmentThirdDirections.actionInformationFragmentThirdToInformationFragmentSecond()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun navigateToAIAnimation(view: View) {
        with(binding) {
            iiAnimation.setOnClickListener {
                val action =
                    InformationFragmentThirdDirections.actionInformationFragmentThirdToCustomIIAnimVisual()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.decorView.windowInsetsController?.
            hide(WindowInsets.Type.navigationBars())
        }
    }
}