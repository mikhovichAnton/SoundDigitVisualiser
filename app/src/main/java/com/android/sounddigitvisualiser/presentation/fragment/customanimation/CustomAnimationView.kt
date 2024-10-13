package com.android.sounddigitvisualiser.presentation.fragment.customanimation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.createBitmap
import java.util.Random
import androidx.lifecycle.LiveData
import com.android.sounddigitvisualiser.domain.model.AnimationCustomModel
import com.android.sounddigitvisualiser.domain.interactor.customview.AnimationFunctionsForCustomView
import com.android.sounddigitvisualiser.domain.repository.local.LogicForCustomV

class CustomAnimationView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
defStyleAttributeSet: Int = 0):View(
    context,
    attributeSet,
    defStyleAttributeSet), LogicForCustomV {

    private lateinit var format: List<IntArray>

    private lateinit var bitmap: Bitmap

    private val paint = Paint()

    private val random = Random()

    private val scale = 6

    private var volume = 2

    private var amplitude = 0

    private var reactionTimer = 50L

    private var scaleFactor = 2.0

    private val animationsForCustomV = AnimationFunctionsForCustomView()

    private var px = 0f
    private var py = 0f
    private var mx1 = 0f
    private var my1 = 0f
    private var mx2 = 0f
    private var my2 = 0f



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val scaledW = w / scale
        val scaledH = h / scale

        format = List(scaledH) { IntArray(scaledW) }

        for (x in 0 until scaledW){
            format[scaledH-1][x] = firePalette.size - 1
        }

        bitmap = createBitmap(scaledW,scaledH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        startAnimations(canvas)
    }

    private fun startAnimations(canvas: Canvas){
        someRandomAnimations(canvas)
        drawingLines(canvas)
        drawingQuads(canvas)
        drawingQ(canvas)
        animationsForCustomV.apply {
                handleVisibilityForCustom(this@CustomAnimationView,volume,amplitude)
                handleRotationForCustom(this@CustomAnimationView,volume,reactionTimer)
                handleScaleForCustom(this@CustomAnimationView,volume, amplitude, scaleFactor)
                handleMovementForCustom(this@CustomAnimationView,volume,reactionTimer,mx1.toInt(),mx2.toInt(),my1.toInt(),my2.toInt())
        }
    }

     override fun someRandomAnimations(canvas: Canvas){
        val vol = random.nextInt((volume / 1000) + 1)
        val si = random.nextInt((volume / 10) + 1)
        paint.apply {
            color = Color.WHITE
            style = Paint.Style.STROKE
            strokeWidth = my2
            isAntiAlias = true
        }
        canvas.drawCircle(px,py,si*random.nextFloat(),paint)
        canvas.drawArc(mx1,si*random.nextFloat(),my1,si*random.nextFloat(),mx2,si*random.nextFloat(),false,paint)
        canvas.drawLine(px,si*random.nextFloat(),py,si*random.nextFloat(),paint)
        drawingQ(canvas)
        invalidate()
    }

       override fun drawingLines(canvas: Canvas){
        val path = Path()
        val vol = random.nextInt((volume / 1000) + 1)
        val si = random.nextInt((volume / 10) + 1)
        path.moveTo(px,py)
        path.lineTo(si * random.nextFloat(),my1)
        path.lineTo(mx1,si * random.nextFloat())
        path.lineTo(si * random.nextFloat(),my1)
        path.lineTo(mx1,si * random.nextFloat())
        path.lineTo(mx2,si * random.nextFloat())
        path.lineTo(si * random.nextFloat(),my1)
        path.lineTo(mx1,si * random.nextFloat())
        path.lineTo(si * random.nextFloat(),my2)
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path,paint)
        invalidate()
    }

     override fun drawingQuads(canvas: Canvas){
        val path = Path()
        val vol = random.nextInt((volume / 1000) + 1)
        val si = random.nextInt((volume / 10) + 1)

        path.moveTo(px,py)
        path.quadTo(mx2,si * random.nextFloat(),mx1,si * random.nextFloat())
        path.quadTo(si * random.nextFloat(),my2,mx2,si * random.nextFloat())
        path.quadTo(mx2,si * random.nextFloat(),mx1,si * random.nextFloat())
        path.quadTo(si * random.nextFloat(),my2,mx2,si * random.nextFloat())
        path.quadTo(si * random.nextFloat(),my1,mx2,si * random.nextFloat())
        path.quadTo(mx1,si * random.nextFloat(),si * random.nextFloat(),my2)
        path.quadTo(si * random.nextFloat(),my1,mx2,si * random.nextFloat())
        path.quadTo(mx1,si * random.nextFloat(),si * random.nextFloat(),my2)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path,paint)
        invalidate()
    }

     override fun drawingQ(canvas: Canvas){
        val path = Path()
        val vol = random.nextInt((volume / 1000) + 1)
        val si = random.nextInt((volume / 10) + 1)
        path.rMoveTo(px,py)
        path.rQuadTo(si * random.nextFloat(),my2,mx2,si * random.nextFloat())
        path.rQuadTo(mx2,si * random.nextFloat(),mx1,si * random.nextFloat())
        path.rQuadTo(si * random.nextFloat(),my2,mx2,si * random.nextFloat())
        path.rQuadTo(mx2,si * random.nextFloat(),mx1,si * random.nextFloat())
        path.rQuadTo(mx1,si * random.nextFloat(),si * random.nextFloat(),my2)
        path.rQuadTo(si * random.nextFloat(),my1,mx2,si * random.nextFloat())
        path.rQuadTo(mx1,si * random.nextFloat(),si * random.nextFloat(),my2)
        path.rQuadTo(si * random.nextFloat(),my1,mx2,si * random.nextFloat())
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path,paint)
        invalidate()
    }



     override fun onPaintingStuff(canvas:Canvas, volume: Int){
            for (y in 0 until format.size - 1){
                for (x in 0 until format[y].size){
                    val dx =  random.nextInt(volume + 1) - 1
                    val dy = random.nextInt((volume / 1000) + 1)
                    val dt = random.nextInt(2)

                    val x1 = Math.min(format[y].size - 1, Math.max(0, x + dx))
                    val y1 = Math.min(format.size - 1, y + dy)
                    val d1 = Math.min (format[x].size - 1 , Math.max(0, format[y1][x1] - dt))
                    format[y][x] = d1
                }
            }
            canvas.scale(scale.toFloat(),scale.toFloat())
            for (y in 0 until format.size){
                for (x in 0 until format[y].size){
                    val color = firePalette[format[y][x]]
                    paint.color = color
                    bitmap.setPixel(x, y,color)
                }
            }
        canvas.drawBitmap(bitmap,0f,random.nextFloat()+(volume/1000).toFloat(), paint)
        invalidate()
    } // ЗАЛИВКА ЦВЕТОМ

    fun setLiveData(initialization: LiveData<AnimationCustomModel>){
        initialization.observeForever { animationData->
            this.volume = animationData.volume
            this.px = animationData.px
            this.py = animationData.py
            this.mx1 = animationData.mx1
            this.my1 = animationData.my1
            this.mx2 = animationData.mx2
            this.my2 = animationData.my2
            this.amplitude = animationData.AMPLITUDE
            this.reactionTimer = animationData.reactionTimer
            this.scaleFactor = animationData.scaleFactor
        }
    }

   companion object{
       private val firePalette = intArrayOf(
           -0xffffff,
           -0xffffff,
           -0xffffff,
           -0xffffff,
           -0xffffff,
           -0xffffff,
           -0xfffff,
           -0xffffff,
           -0xffffff,
           -0xffffff,
           -0xffffff,
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
   }
}