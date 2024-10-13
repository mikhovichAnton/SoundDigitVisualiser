package com.android.sounddigitvisualiser.presentation.fragment.startinginformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        navigateToImageAnimation(view)
    }

    private fun navigateToImageAnimation(view: View) {
        with(binding) {
            imageAnimation.setOnClickListener {
                val animationImageModel = AnimationImageModel(0,"FAKE",0,0,0,0,0.0,0,0,0,0)
                val action = InformationFragmentSecondDirections.actionInformationFragmentSecondToViewAnimatorFragment(animationImageModel)
                view.findNavController().navigate(action)
            }
        }
    }


    //ToDoo
//    private fun navigateToNextAnimation(view: View){
//        with(binding){
//            nextAnimation.setOnClickListener {
//                val action = InformationFragmentFirstDirections.
//                actionInformationFragmentFirstToInformationFragmentSecond()
//                view.findNavController().navigate(action)
//            }
//        }
//    }
}