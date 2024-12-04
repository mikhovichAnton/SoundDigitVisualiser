package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    private var random = java.util.Random()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideNavigationBar()
        textAnim()

        android.os.Handler().postDelayed({
            val action =
                SplashScreenFragmentDirections.actionSplashScreenFragmentToInformationFragmentFirst()
            view.findNavController().navigate(action)
        }, 4000)
    }

    private fun textAnim() {
        binding.sdvText.animate().alpha(random.nextFloat()).setInterpolator(OvershootInterpolator())
            .setDuration(10000).start()
    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())
        }
    }

}