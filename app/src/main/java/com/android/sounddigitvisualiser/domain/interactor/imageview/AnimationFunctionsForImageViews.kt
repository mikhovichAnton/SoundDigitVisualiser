package com.android.sounddigitvisualiser.domain.interactor.imageview


import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import com.android.sounddigitvisualiser.R
import com.android.sounddigitvisualiser.domain.interactor.animationtypes.MovementAnimationTypes
import com.android.sounddigitvisualiser.domain.interactor.animationtypes.RotationAnimationTypes
import com.android.sounddigitvisualiser.domain.repository.local.AnimationsForImages
import kotlin.random.Random

class AnimationFunctionsForImageViews : AnimationsForImages {

    private val rotationAnimationTypes = RotationAnimationTypes()

    private val movementAnimationTypes = MovementAnimationTypes()

    private lateinit var movementAnimationArray: Array<TranslateAnimation>

    private lateinit var rotationAnimationArray: Array<ViewPropertyAnimator>

    override fun handleVisibility(list: List<View>, volume: Int, MAX_RECORD_AMPLITUDE: Int) {
        var scale = volume / MAX_RECORD_AMPLITUDE.toFloat() + 0.2f
        scale = Math.min(scale, 3.0f)
        for (item in list) {
            item.animate()
                .alpha(scale)
                .setInterpolator(OvershootInterpolator(volume.toFloat() / 10))
                .setDuration(50)
        }
    }

    override fun handleScale(
        list: List<View>,
        volume: Int,
        MAX_RECORD_AMPLITUDE: Int,
        scaleFactor: Double
    ) {
        var scale = volume / MAX_RECORD_AMPLITUDE.toFloat() + 0.5f
        scale = Math.max(scale.toDouble(), scaleFactor).toFloat()
        for (item in list) {
            item.animate()
                .scaleX(scale)
                .scaleY(scale)
                .setInterpolator(OvershootInterpolator())
                .setDuration((Math.min(2, (volume / 1000).toLong() / 2)))
        }
    }

    override fun handleRotation(array: Array<View>, volume: Int, reactionTimer: Long) {
        for (item in array) {
            rotationAnimationArray =
                rotationAnimationTypes.rotationAnimations(item, volume, reactionTimer)
            var vol = Math.min((volume / 10000) / 2, rotationAnimationArray.size - 1)
            if ((vol + 1) >= rotationAnimationArray.size) {
                array[vol - 1].animate().apply { rotationAnimationArray[0] }
            } else {
                array[vol].animate().apply { rotationAnimationArray[vol] }
            }

        }
    }

    override fun onRandomColorChange(array: Array<View>, volume: Int) {
        for (item in array) {
            var vol = Random.nextInt((volume / 10000) + 2, firePalette.size - 1)
            if ((vol + 1) >= firePalette.size) {
                item.background.setTint(firePalette[0])
            } else {
                item.background.setTint(firePalette[vol])
            }
        }
    }

    override fun handleColorChange(array: Array<View>, param: Int) {
        for (item in array) {
            item.background.setTint(firePalette[param])
        }
    }

    override fun handleRandomFormChanging(list: List<View>, volume: Int) {
        for (item in list) {
            var vol = Random.nextInt((volume / 2) / 1000, formPalette.size - 1)
            if ((vol + 1) >= formPalette.size) {
                item.setBackgroundResource(formPalette[0])
            } else {
                item.setBackgroundResource(formPalette[vol])
            }
        }
    }

    override fun handleChangeForm(list: List<View>, volume: Int, formNumber: Int) {
        for (item in list) {
//            var vol = Random.nextInt((volume / 10000) / 2, array.size - 1)
//            val vol = (volume / 10000)
            item.setBackgroundResource(formPalette[formNumber])
        }
    }

    override fun handleMovement(
        list: List<View>,
        volume: Int,
        reactionTimer: Long,
        fromX: Int,
        toX: Int,
        fromY: Int,
        toY: Int
    ) {

        for (item in list) {
            movementAnimationArray = movementAnimationTypes.movementAnimations(
                volume,
                reactionTimer,
                fromX,
                toX,
                fromY,
                toY
            )
//            val vol = Random.nextInt((volume / 10000) + 2, movementAnimationArray.size - 1)
          val vol = (volume / 1000)
            if ((vol + 1) >= movementAnimationArray.size) {
                item.startAnimation(movementAnimationArray[0])
            } else {
                item.startAnimation(movementAnimationArray[vol])
            }
        }
    }

    companion object {
        private val firePalette = intArrayOf(
            -0x22de08,
            -0xefeb95,
            -0x5c1f8b,
            -0xaf11c0,
            -0x0126ca,
            -0xda441b,
            -0xc56b59,
            -0x5b1a15,
            -0x823a1c,
            -0xa2322a,
            -0xb6dfb3,
            -0xc4d7a3,
            -0x5aa51d,
            -0x6bba4f,
            -0x71c341,
            -0x6793e9,
            -0x9099c6,
            -0xabb5cf,
            -0x3646a1,
            -0x9e9ee0,
            -0x2c6f9d,
            -0x24ebb0,
            -0xea9985,
            -0x1db43e
        )

        private val formPalette = arrayOf(
            R.drawable.dots_in_cirle,
            R.drawable.moon,
            R.drawable.cycle_way,
            R.drawable.oval,
            R.drawable.halfcircle_dots,
            R.drawable.dots,
            R.drawable.double_circle,
            R.drawable.kolo,
            R.drawable.razor_sun,
            R.drawable.znak,
            R.drawable.rounded_dots
        )
    }
}
