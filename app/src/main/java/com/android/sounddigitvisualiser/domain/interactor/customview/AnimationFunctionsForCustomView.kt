package com.android.sounddigitvisualiser.domain.interactor.customview

import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.OvershootInterpolator
import android.view.animation.TranslateAnimation
import com.android.sounddigitvisualiser.domain.interactor.animationtypes.MovementAnimationTypes
import com.android.sounddigitvisualiser.domain.interactor.animationtypes.RotationAnimationTypes
import com.android.sounddigitvisualiser.domain.repository.local.AnimationsForCustomV

class AnimationFunctionsForCustomView: AnimationsForCustomV {

    private val rotationAnimationTypes = RotationAnimationTypes()

    private val movementAnimationTypes = MovementAnimationTypes()

    private lateinit var movementAnimationArray: Array<TranslateAnimation>

    private lateinit var rotationAnimationArray: Array<ViewPropertyAnimator>


     override fun handleVisibilityForCustom(view: View, volume: Int, MAX_RECORD_AMPLITUDE: Int) {
        var scale = volume / MAX_RECORD_AMPLITUDE.toFloat() + 0.2f
        scale = Math.min(scale, 3.0f)
        view.animate()
            .alpha(scale)
            .setInterpolator(OvershootInterpolator(volume.toFloat() / 10))
            .setDuration(50)
    }

    override fun handleScaleForCustom(view: View, volume: Int, MAX_RECORD_AMPLITUDE: Int, scaleFactor:Double) {
        var scale = volume / MAX_RECORD_AMPLITUDE.toFloat() + 0.5f
        scale = Math.min(scale.toDouble(),scaleFactor).toFloat()
        view.animate()
            .scaleX(scale)
            .scaleY(scale)
            .setInterpolator(OvershootInterpolator())
            .setDuration((Math.min(2, (volume / 10000).toLong())))
    }

    override fun handleRotationForCustom(view: View, volume: Int, reactionTimer: Long) {
        rotationAnimationArray = rotationAnimationTypes.rotationAnimations(view,volume,reactionTimer)
        var vol = Math.min((volume / 1000) / 2, rotationAnimationArray.size - 1)
        if ((vol + 1) >= rotationAnimationArray.size){
            view.animate().apply {rotationAnimationArray[0]}
        } else {
            view.animate().apply {rotationAnimationArray[vol]}
        }
    }

    override fun handleMovementForCustom(view: View, volume: Int, reactionTimer: Long, fromX: Int, toX: Int, fromY: Int, toY: Int) {
        movementAnimationArray = movementAnimationTypes.movementAnimations(volume, reactionTimer,fromX,toX,fromY,toY)
//            val vol = Random.nextInt((volume / 10000) + 2, movementAnimationArray.size - 1)
        val vol = (volume / 1000)
        if ((vol+1) >= movementAnimationArray.size){
            view.startAnimation(movementAnimationArray[0])
        } else{
            view.startAnimation(movementAnimationArray[vol])
        }
    }
}