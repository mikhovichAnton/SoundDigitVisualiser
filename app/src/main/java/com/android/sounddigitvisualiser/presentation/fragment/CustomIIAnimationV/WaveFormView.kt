package com.android.sounddigitvisualiser.presentation.fragment.CustomIIAnimationV

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class WaveformView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttributeSet: Int = 0):View(
    context,
    attributeSet,
    defStyleAttributeSet) {

    private val paint = Paint()
    private val path = Path()
    private var shapes: MutableList<Shape> = mutableListOf() // Список фигур
    private val shapeSpeed = 10f // Скорость перемещения фигур
    private val canvas = Canvas()

    init {
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 5f
    }

    fun updateWaveform(amplitude: Float) {
        path.reset()
        val width = width.toFloat()
        val height = height.toFloat()

        // Меняем цвет
        paint.color = Color.rgb(
            (255 * Math.random()).toInt(),
            (255 * Math.random()).toInt(),
            (255 * Math.random()).toInt()
        )

        // Образуем новые фигуры
        if (Math.random() < 0.2) { // Увеличиваем вероятность
            val newShape = createRandomShape(amplitude)
            shapes.add(newShape)
        }

        // Перемещаем и рисуем все существующие фигуры
        for (shape in shapes) {
            shape.move(width, height, amplitude) // Передаем амплитуду для изменения
            shape.draw(canvas)
        }

        // Отрисовка волны
        path.moveTo(0f, height / 2)
        for (x in 0 until width.toInt()) {
            val y: Float = height / 2 + amplitude * (Math.sin(x * 0.05) * 50).toFloat() // Увеличиваем амплитуду
            path.lineTo(x.toFloat(), y)
        }

        invalidate() // Перезапускаем отрисовку
    }

    private fun createRandomShape(amplitude: Float): Shape {
        val centerX = (Math.random() * width).toFloat()
        val centerY = (Math.random() * height).toFloat()
        val radius = (amplitude * height / 10).coerceIn(10f, (height / 4).toFloat()) // Изменяем количество радиуса
        val shapeType = (0..2).random() // 0 - окружность, 1 - квадрат, 2 - треугольник
        val randomColor = Color.rgb((255 * Math.random()).toInt(), (255 * Math.random()).toInt(), (255 * Math.random()).toInt())

        return Shape(centerX, centerY, radius, shapeType, randomColor)
    }

    private inner class Shape(var centerX: Float, var centerY: Float, private var radius: Float, private val type: Int, private val color: Int) {
        private var velocityX = (Math.random() * 4 - 2) * shapeSpeed // Случайная скорость по оси X
        private var velocityY = (Math.random() * 4 - 2) * shapeSpeed // Случайная скорость по оси Y

        fun move(width: Float, height: Float, amplitude: Float) {
            centerX += velocityX.toFloat()
            centerY += velocityY.toFloat()

            // Проверка границ для случайного изменения направления и скорости
            if (centerX < 0 || centerX > width) {
                velocityX = -velocityX + (Math.random() * 2 - 1) // Случайное изменение направления
                radius = (amplitude * height / 10).coerceIn(10f, height / 4) // К примеру, изменяем радиус
            }
            if (centerY < 0 || centerY > height) {
                velocityY = -velocityY + (Math.random() * 2 - 1) // Случайное изменение направления
                radius = (amplitude * height / 10).coerceIn(10f, height / 4) // Изменяем радиус по амплитуде
            }
        }

        fun draw(canvas: Canvas) {
            paint.color = color
            when (type) {
                0 -> canvas.drawCircle(centerX, centerY, radius, paint) // Окружность
                1 -> canvas.drawRect(centerX - radius, centerY - radius, centerX + radius, centerY + radius, paint) // Квадрат
                2 -> { // Треугольник
                    val pathTriangle = Path().apply {
                        moveTo(centerX, centerY - radius) // Вершина верхнего
                        lineTo(centerX - radius, centerY + radius) // Левый низ
                        lineTo(centerX + radius, centerY + radius) // Правый низ
                        close() // Закрываем путь
                    }
                    canvas.drawPath(pathTriangle, paint) // Треугольник
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }
}