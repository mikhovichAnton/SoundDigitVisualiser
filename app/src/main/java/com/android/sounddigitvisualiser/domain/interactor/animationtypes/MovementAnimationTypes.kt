package com.android.sounddigitvisualiser.domain.interactor.animationtypes

import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation

class MovementAnimationTypes {


    private var duration = 0L

    fun movementAnimations(volume: Int, reactionTimer: Long,fromX: Int, toX: Int, fromY: Int, toY: Int) : Array<TranslateAnimation> {

        val interpolator = BounceInterpolator()

        duration = if (volume < 1000) {
            0L
        } else {
            ((volume + 50) / (reactionTimer + 50))
        }
        val animation = TranslateAnimation(
            0.0f,
            0.0f,
            0.0f,
            0.0f
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation1 = TranslateAnimation(
            fromX.toFloat(),
            toX.toFloat(),
            fromY.toFloat(),
            toY.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }

        val animation2 = TranslateAnimation(
            fromX.toFloat(),
            toY.toFloat(),
            fromY.toFloat(),
            toX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation3 = TranslateAnimation(
            toX.toFloat(),
            fromY.toFloat(),
            fromX.toFloat(),
            toY.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation4 = TranslateAnimation(
            toX.toFloat(),
            toY.toFloat(),
            fromX.toFloat(),
            fromY.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation5 = TranslateAnimation(
            toY.toFloat(),
            toX.toFloat(),
            fromY.toFloat(),
            fromX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation6 = TranslateAnimation(
            toY.toFloat(),
            fromX.toFloat(),
            toX.toFloat(),
            fromY.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation7 =  TranslateAnimation(
            fromY.toFloat(),
            toY.toFloat(),
            fromX.toFloat(),
            toX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation8 =  TranslateAnimation(
            fromY.toFloat(),
            fromX.toFloat(),
            toY.toFloat(),
            toX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation9 =  TranslateAnimation(
            fromX.toFloat(),
            fromX.toFloat(),
            fromY.toFloat(),
            toX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation10 =  TranslateAnimation(
            fromY.toFloat(),
            toY.toFloat(),
            fromY.toFloat(),
            toX.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animation11 =  TranslateAnimation(
            fromY.toFloat(),
            toX.toFloat(),
            fromY.toFloat(),
            toY.toFloat()
        ).apply {
            this.interpolator = interpolator
            this.duration = duration
            this.repeatCount = duration.toInt()
        }
        val animationArray: Array<TranslateAnimation> =
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
                animation10,
                animation11
            )
        return animationArray
    }
}