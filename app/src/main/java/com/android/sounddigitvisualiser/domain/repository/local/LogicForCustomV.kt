package com.android.sounddigitvisualiser.domain.repository.local

import android.graphics.Canvas

interface LogicForCustomV {
    fun someRandomAnimations(canvas: Canvas)
    fun drawingLines(canvas: Canvas)
    fun drawingQuads(canvas: Canvas)
    fun drawingQ(canvas: Canvas)
    fun onPaintingStuff(canvas:Canvas, volume: Int)
}