package com.android.sounddigitvisualiser.domain.repository.local

import android.view.View

interface AnimationsForImages {
    fun handleVisibility(list: List<View>, volume: Int, MAX_RECORD_AMPLITUDE: Int)
    fun handleScale(list: List<View>, volume: Int, MAX_RECORD_AMPLITUDE: Int, scaleFactor: Double)
    fun handleRotation(array: Array<View>, volume: Int, reactionTimer: Long)
    fun onRandomColorChange(array: Array<View>, volume: Int)
    fun handleColorChange(array: Array<View>,param: Int)
    fun handleRandomFormChanging(list: List<View>, volume: Int)
    fun handleChangeForm(list: List<View>, volume: Int, formNumber: Int)
    fun handleMovement(list: List<View>, volume: Int, reactionTimer: Long,fromX: Int, toX: Int, fromY: Int, toY: Int)
}