package personal.compass

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.view.View

class CCompass(context: Context): View(context) {
    private val paint: Paint
    private var direction = 0f

    init{
        paint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        paint.isAntiAlias = true
        paint.strokeWidth = 2f
        paint.textSize = 25f
        paint.style = Paint.Style.STROKE
        paint.color =  Color.parseColor("#A69796")
    }

    fun updateData(position: Float){
        direction = position
        invalidate();
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        )

    }

    override fun onDraw(canvas: Canvas) {
        val xP = measuredWidth / 2
        val yP = measuredHeight / 2
        val radius = (Math.min(xP,yP) * 0.8).toFloat()
        canvas.drawCircle(xP.toFloat(), yP.toFloat(), radius, paint)
        //canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), paint)
        canvas.drawLine(
            xP.toFloat(), yP.toFloat(),
            (xP + radius * Math.sin(-direction.toDouble())).toFloat(),
            (yP - radius * Math.cos(-direction.toDouble())).toFloat(), paint)
        canvas.drawText("%6.2f".format(180*direction/Math.PI), xP.toFloat(), yP.toFloat(), paint)

    }





}