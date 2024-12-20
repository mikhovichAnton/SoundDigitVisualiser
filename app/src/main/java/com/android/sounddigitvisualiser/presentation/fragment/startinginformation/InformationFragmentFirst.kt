package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentInformationFirstBinding


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
        hideNavigationBar()
        navigateToCustomAnimation(view)
        navigateToNextAnimation(view)
    }

    private fun navigateToCustomAnimation(view: View) {
        with(binding) {
            customAnimation.setOnClickListener {
                val action =
                    InformationFragmentFirstDirections.
                    actionInformationFragmentFirstToVisualCustomViewFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun navigateToNextAnimation(view: View) {
        with(binding) {
            nextAnimation.setOnClickListener {
                val action =
                    InformationFragmentFirstDirections.
                    actionInformationFragmentFirstToInformationFragmentSecond()
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