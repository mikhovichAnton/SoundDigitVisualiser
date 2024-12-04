package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentInformationSecondBinding
import com.android.sounddigitvisualiser.domain.model.AnimationImageModel


class InformationFragmentSecond : Fragment() {

    private lateinit var binding: FragmentInformationSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideNavigationBar()
        navigateToPreviousAnimation(view)
        navigateToImageAnimation(view)
        navigateToNextAnimation(view)
    }

    private fun navigateToPreviousAnimation(view: View) {
        with(binding) {
            toBackAnimation.setOnClickListener {
                val action =
                    InformationFragmentSecondDirections.actionInformationFragmentSecondToInformationFragmentFirst()
                view.findNavController().navigate(action)
            }
        }
    }

    private fun navigateToImageAnimation(view: View) {
        with(binding) {
            imageAnimation.setOnClickListener {
                val animationImageModel = AnimationImageModel(
                    0,
                    "FAKE",
                    0, 0, 0, 0, 0.0, 0,
                    0, 0, 0
                )
                val action =
                    InformationFragmentSecondDirections.actionInformationFragmentSecondToViewAnimatorFragment(
                        animationImageModel
                    )
                view.findNavController().navigate(action)
            }
        }
    }

    private fun navigateToNextAnimation(view: View) {
        with(binding) {
            nextAnimation.setOnClickListener {
                val action =
                    InformationFragmentSecondDirections.actionInformationFragmentSecondToInformationFragmentThird()
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