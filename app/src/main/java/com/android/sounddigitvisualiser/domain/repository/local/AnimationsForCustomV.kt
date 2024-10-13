package com.android.sounddigitvisualiser.domain.repository.local

import android.view.View

interface AnimationsForCustomV {
    fun handleVisibilityForCustom(view: View, volume: Int, MAX_RECORD_AMPLITUDE: Int)
    fun handleScaleForCustom(view: View, volume: Int, MAX_RECORD_AMPLITUDE: Int,scaleFactor:Double)
    fun handleRotationForCustom(view: View, volume: Int, reactionTimer: Long)
    fun handleMovementForCustom(view: View, volume: Int, reactionTimer: Long,fromX: Int, toX: Int, fromY: Int, toY: Int)
}