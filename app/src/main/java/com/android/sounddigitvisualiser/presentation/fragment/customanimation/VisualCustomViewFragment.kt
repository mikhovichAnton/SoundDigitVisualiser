package com.android.sounddigitvisualiser.presentation.fragment.customanimation

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.SeekBar
import com.android.sounddigitvisualiser.domain.controllers.AudioController
import com.android.sounddigitvisualiser.databinding.FragmentVisualCustomViewBinding
import com.android.sounddigitvisualiser.domain.repository.local.navigation.FragmentNavigation
import com.android.sounddigitvisualiser.presentation.viewmodel.AnimationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class VisualCustomViewFragment : Fragment() {

    private lateinit var binding: FragmentVisualCustomViewBinding

    private lateinit var contextLate: Context

    private lateinit var audioController: AudioController

    private var countDownTimer: CountDownTimer? = null

    private var volume = 2

    private var scaleFactor = 2.0

    private var reactionTimer = 50L

    private var px = 0f
    private var py = 0f
    private var mx1 = -1500f
    private var my1 = -1500f
    private var mx2 = -1500f
    private var my2 = -15f

    private val animationViewModel: AnimationViewModel by viewModel<AnimationViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextLate = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisualCustomViewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideNavigationBar()
        requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_CODE)


        binding.apply {
            buttonStart2.setOnClickListener {
                buttonStart2.visibility = View.INVISIBLE
                onButtonClicked()
            }
            buttonStop2.setOnClickListener {
                navigateToMainFragment()
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
                        customOne.setLiveData(animationViewModel.initialization)
                        onChangingParameters(volume)
                    }
                }

                override fun onFinish() {

                }
            }
            countDownTimer?.start()
        }

    }

    private fun hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity()
                .window
                .decorView
                .windowInsetsController?.hide(WindowInsets.Type.navigationBars())
        }
    }

    private fun onChangingParameters(volume: Int) {

            this.binding.apply {

                pointX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {


                      px = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                pointY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                     py = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                fx1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                       mx1 = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                fy1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                       my1 = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                fx2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                       mx2 = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                fy2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        my2 = p1.toFloat()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                forScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        scaleFactor = p1.toDouble()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })
                reactionSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        reactionTimer = p1.toLong()

                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })


                animationViewModel.setLiveParams(volume,
                    px,py,mx1,mx2,my1,my2, MAX_RECORD_AMPLITUDE,reactionTimer,scaleFactor)
        }
    }

    private fun navigateToMainFragment(){
        apply {
            val navigate = this.activity as FragmentNavigation
            navigate.navigationFrag(VisualCustomViewFragment(),false)
            audioController.reset()
        }
    }

    companion object {
        private const val REQUEST_CODE = 182
        private const val MAX_RECORD_AMPLITUDE = 23000
    }
}