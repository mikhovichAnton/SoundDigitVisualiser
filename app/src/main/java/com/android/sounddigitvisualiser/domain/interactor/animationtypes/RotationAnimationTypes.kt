package com.android.sounddigitvisualiser.domain.interactor.animationtypes

import android.util.Log
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.OvershootInterpolator

class RotationAnimationTypes {

    private var duration = 0L

    fun rotationAnimations(item: View, volume: Int, reactionTimer: Long): Array<ViewPropertyAnimator> {

        duration = if (volume < 1000){
            0L
        } else {
            (volume - reactionTimer) /2
        }
        Log.d("Reaction =", "$duration")
        val animation = item.animate()
            .rotation(volume.toFloat() / 5000)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation1 = item.animate()
            .rotation(volume.toFloat() / 4000)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation2 = item.animate()
            .rotation(volume.toFloat() / 3000)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation3 = item.animate()
            .rotation(volume.toFloat() / 2500)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation4 = item.animate()
            .rotation(volume.toFloat() / 1750)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation5 = item.animate()
            .rotation(volume.toFloat() / 875)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation6 = item.animate()
            .rotation(volume.toFloat() / 432)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)


        val animation7 = item.animate()
            .rotation(volume.toFloat() / 216)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation8 = item.animate()
            .rotation(volume.toFloat() / 108)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation9 = item.animate()
            .rotation(volume.toFloat() / 54)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation0 = item.animate()
            .rotation(volume.toFloat() / 27)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animation11 = item.animate()
            .rotation(volume.toFloat() / 13)
            .setInterpolator(OvershootInterpolator())
            .setDuration(duration)

        val animationArray: Array<ViewPropertyAnimator> =
            arrayOf(
                animation,
                animation1,
                animation2,
                animation3,
                animation4,
                animation5,
                animation6,
                animation7,
                animation8,
                animation9,
                animation0,
                animation11
            )
        return animationArray
    }
}