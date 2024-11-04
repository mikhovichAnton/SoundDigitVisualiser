package com.android.sounddigitvisualiser.presentation.fragment.imageviewanimation

import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.sounddigitvisualiser.R
import com.android.sounddigitvisualiser.databinding.FragmentViewAnimatorBinding
import com.android.sounddigitvisualiser.domain.controllers.AudioController
import com.android.sounddigitvisualiser.domain.interactor.imageview.AnimationFunctionsForImageViews
import com.android.sounddigitvisualiser.domain.model.AnimationImageModel
import com.android.sounddigitvisualiser.domain.repository.local.navigation.FragmentNavigation
import com.android.sounddigitvisualiser.presentation.viewmodel.AnimationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ViewAnimatorFragment : Fragment() {

    private lateinit var binding: FragmentViewAnimatorBinding
    private lateinit var contextLate: Context
    private lateinit var audioController: AudioController
    private var countDownTimer: CountDownTimer? = null

    private val args: ViewAnimatorFragmentArgs by navArgs()
    private val animationViewModel: AnimationViewModel by viewModel<AnimationViewModel>()
    private val animationFunctionsForImageViews = AnimationFunctionsForImageViews()

    private lateinit var list: List<View>
    private lateinit var array: Array<View>
    private lateinit var startingAnimationImageModel: AnimationImageModel

    private var scaleFactor = 2.0
    private var reactionTimer = 75L
    private var formNumber = 0
    private var paramForColor = 0
    private var volume = 0
    private var fromX = -25
    private var tx = 700
    private var fromY = -150
    private var ty = 500


    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextLate = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewAnimatorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        hideNavigationBar()

        requestPermissions(arrayOf(RECORD_AUDIO), REQUEST_CODE)

        audioController = AudioController(contextLate)
        makeList()
        makeArray()

        startingAnimationImageModel = AnimationImageModel(
            0,
            "ANIMO",
            fromX,
            tx,
            fromY,
            ty,
            scaleFactor,
            reactionTimer,
            formNumber,
            paramForColor,
            volume)

        checkParametersAndArgs(startingAnimationImageModel)



        binding.apply {
            buttonSave.setOnClickListener {
                saveAnimationPreset()
            }
            buttonLoad.setOnClickListener{
                navigateToLoadingParamsFragment(view)
            }
            buttonStart.setOnClickListener {
                buttonStart.visibility = View.INVISIBLE
                onButtonClicked()
            }
            buttonStop.setOnClickListener {
                navigateToMainFragment()
            }
            menuButton.setOnClickListener {
                if (dropdownMenu.visibility == View.VISIBLE) {
                    dropdownMenu.visibility = View.INVISIBLE
                } else {
                    dropdownMenu.visibility = View.VISIBLE
                }
                if (buttonStart.visibility == View.VISIBLE) {
                    buttonStop.visibility = View.INVISIBLE
                } else {
                    buttonStop.visibility = View.VISIBLE
                }
                if (buttonStop.visibility == View.VISIBLE) {
                    buttonRForm.visibility = View.VISIBLE
                } else {
                    buttonRForm.visibility = View.INVISIBLE
                }
                if (buttonStop.visibility == View.VISIBLE) {
                    buttonRColor.visibility = View.VISIBLE
                } else {
                    buttonRColor.visibility = View.INVISIBLE
                }
            }

        }
    }

    private fun saveAnimationPreset(){
        with(binding){

            val name = etParameterNameInput.text.toString()
            if (name.isNotEmpty()) {
                animationViewModel.initImage.observe(
                    viewLifecycleOwner,
                    Observer { animationImage ->
                          startingAnimationImageModel = animationImage
                    })
                animationViewModel.saveAnim(
                    startingAnimationImageModel.id,
                    name = name,
                    startingAnimationImageModel.fromX,
                    startingAnimationImageModel.toX,
                    startingAnimationImageModel.fromY,
                    startingAnimationImageModel.toY,
                    startingAnimationImageModel.formNumber,
                    startingAnimationImageModel.paramForColor,
                    startingAnimationImageModel.reactionTimer,
                    startingAnimationImageModel.scaleFactor,
                    startingAnimationImageModel.volume
                )
                Toast.makeText(contextLate,"Parameter Saved as $name",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(contextLate,"Fill primary field",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkParametersAndArgs(animationImageModel: AnimationImageModel){
        if (args.currentParameters.parameterName == animationImageModel.parameterName){
            Toast.makeText(contextLate, "Current Animation parameter is ${animationImageModel.parameterName}",Toast.LENGTH_SHORT).show()
            setLoadedAnimationParameter(animationImageModel)
        }else{
            setLoadedAnimationParameter(args.currentParameters)
        }
    }

    private fun setLoadedAnimationParameter(imageModel: AnimationImageModel){
        with(binding){
            pivotX.progress = imageModel.fromX
            pivotY.progress = imageModel.fromY
            toX.progress = imageModel.toX
            toY.progress = imageModel.toY
            forScale.progress = imageModel.scaleFactor.toInt()
            formChange.progress = imageModel.formNumber
            colorSeekBar.progress = imageModel.paramForColor
            reactionDuration.progress = imageModel.reactionTimer.toInt()
        }
        animationViewModel.setLiveParamsForImage(
            id = imageModel.id,
            name = imageModel.parameterName,
            fromX = imageModel.fromX,
            toX = imageModel.toX,
            fromY = imageModel.fromY,
            toY = imageModel.toY,
            formNumber = imageModel.formNumber,
            paramForColor = imageModel.paramForColor,
            reactionTimer = imageModel.reactionTimer,
            scaleFactor = imageModel.scaleFactor,
            volume = volume
        )
    }

    private fun navigateToLoadingParamsFragment(view: View) {
        val action = ViewAnimatorFragmentDirections.actionViewAnimatorFragmentToLoadingParameters()
        this.findNavController().navigate(action)
    }
    private fun navigateToMainFragment() {
        apply {
            val navigate = this.activity as FragmentNavigation
            navigate.navigationFrag(ViewAnimatorFragment(), false)
            audioController.reset()
        }
    }

    private fun onChangingParameters(volume: Int) {
        this.binding.apply {
            animationFunctionsForImageViews.apply {
                pivotX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        fromX = p1


                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                })

                pivotY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        fromY = p1
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })

                toX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                        tx = p1
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                    }
                })

                toY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

                        ty = p1

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

                reactionDuration.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                        reactionTimer = p1.toLong()
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {

                    }

                })
                colorSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                        paramForColor = p1
                        handleColorChange(array, paramForColor)
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                        buttonRColor.setOnClickListener {
                            onRandomColorChange(array, volume)
                        }
                    }

                })
                formChange.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                        formNumber = p1
                        handleChangeForm(list, volume, formNumber)
                    }

                    override fun onStartTrackingTouch(p0: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(p0: SeekBar?) {
                        buttonRForm.setOnClickListener {
                            handleRandomFormChanging(list, volume)
                        }
                    }
                })
                animationViewModel.setLiveParamsForImage(
                    0,
                    "ANIMO",
                    fromX,
                    tx,
                    fromY,
                    ty,
                    formNumber,
                    paramForColor,
                    reactionTimer,
                    scaleFactor,
                    volume
                )
            }
        }
    }

    private fun onButtonClicked() {
        if (audioController.isAudioInputing()) {
            audioController.stop()
            countDownTimer?.onFinish()
            countDownTimer = null
        } else {
            audioController.start()
            countDownTimer = object : CountDownTimer(maxOf(1000000_0000L), reactionTimer) {
                override fun onTick(p0: Long) {
                    volume = audioController.getVolume()
                    onChangingParameters(volume)
                    setLiveData(animationViewModel.initImage)
                    animationFunctionsForImageViews.apply {
                        handleRotation(array, volume, reactionTimer)
                        handleVisibility(list, volume, MAX_RECORD_AMPLITUDE)
                        handleScale(list, volume, MAX_RECORD_AMPLITUDE, scaleFactor)
//                        handleMovement(list, volume, reactionTimer, fromX, tx, fromY, ty)
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
            requireActivity().window.decorView.windowInsetsController?.hide(WindowInsets.Type.navigationBars())
        }
    }

    fun setLiveData(initImage: LiveData<AnimationImageModel>) {
        initImage.observeForever { animationData ->
            this.volume = animationData.volume
            this.fromX = animationData.fromX
            this.tx = animationData.toX
            this.fromY = animationData.fromY
            this.ty = animationData.toY
            this.scaleFactor = animationData.scaleFactor
            this.paramForColor = animationData.paramForColor
            this.reactionTimer = animationData.reactionTimer
            this.formNumber = animationData.formNumber
        }
    }

    private fun makeList() {
        binding.apply {
            list = listOf(
                visualQuatro,
                visualQuatro2,
                visualQuatro3,
                visualQuatro4,
                visualTriang,
                visualTriang2,
                visualTriang3,
                visualTriang4,
                visualQuatro5,
                visualQuatro6,
                visualQuatro7,
                visualQuatro8,
                visualQuatro,
                visualQuatro8
            )
        }
    }

    private fun makeArray() {
        binding.apply {
            array = arrayOf(
                visualQuatro,
                visualQuatro2,
                visualQuatro3,
                visualQuatro4,
                visualTriang,
                visualTriang2,
                visualTriang3,
                visualTriang4,
                visualQuatro5,
                visualQuatro6,
                visualQuatro7,
                visualQuatro8
            )
        }
    }

    companion object {
        private const val REQUEST_CODE = 182
        private const val MAX_RECORD_AMPLITUDE = 23000
    }

}
