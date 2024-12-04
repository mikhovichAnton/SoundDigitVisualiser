package com.android.sounddigitvisualiser.presentation.fragment.CustomIIAnimationV

import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.sounddigitvisualiser.databinding.FragmentCustomIIAnimVisualBinding
import com.android.sounddigitvisualiser.domain.controllers.AudioController

class CustomIIAnimVisual : Fragment() {

    private lateinit var binding: FragmentCustomIIAnimVisualBinding

    private lateinit var contextLate: Context

    private lateinit var audioController: AudioController

    private var countDownTimer: CountDownTimer? = null

    private var volume = 2

    private var reactionTimer = 50L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextLate = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomIIAnimVisualBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions(arrayOf(RECORD_AUDIO), REQUEST_CODE)

        binding.apply {
            backButton.setOnClickListener {
                navigateToInfoMenu(view)
            }
            buttonStart2.setOnClickListener {
                buttonStart2.visibility = View.INVISIBLE
                onButtonClicked()
            }
            buttonStop2.setOnClickListener {
                //Need to add some Logic Here
            }
            menuButton2.setOnClickListener {
                if (dropdownMenu2.visibility == View.VISIBLE){
                    dropdownMenu2.visibility = View.INVISIBLE
                } else {
                    dropdownMenu2.visibility = View.VISIBLE
                }
                if (buttonStart2.visibility==View.VISIBLE){
                    buttonStop2.visibility = View.INVISIBLE
                }else{
                    buttonStop2.visibility = View.VISIBLE
                }
                if (buttonStop2.visibility==View.VISIBLE){
                    buttonRForm.visibility=View.VISIBLE
                } else{
                    buttonRForm.visibility=View.INVISIBLE
                }
                if (buttonStop2.visibility==View.VISIBLE){
                    buttonRColor.visibility=View.VISIBLE
                }else{
                    buttonRColor.visibility=View.INVISIBLE
                }
            }

        }
    }

    private fun navigateToInfoMenu(view: View) {
        val action = CustomIIAnimVisualDirections.actionCustomIIAnimVisualToInformationFragmentThird()
        view.findNavController().navigate(action)
    }

    private fun onButtonClicked() {
        audioController = AudioController(contextLate)
        if (audioController.isAudioInputing()) {
            audioController.stop()
            countDownTimer?.onFinish()
            countDownTimer = null
        } else {
            audioController.start()
            countDownTimer = object : CountDownTimer(maxOf(1000000_0000L), reactionTimer) {
                override fun onTick(p0: Long) {
                    volume = audioController.getVolume()
                    binding.apply {
                        customIi.updateWaveform(volume.toFloat())
                    }
                }

                override fun onFinish() {

                }
            }
            countDownTimer?.start()
        }
    }
    companion object {
        private const val REQUEST_CODE = 182
        private const val MAX_RECORD_AMPLITUDE = 23000
    }
}