package com.example.xfermoddemo

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class BeautyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val BITMAP_WIDTH = 300.dp

    val bitmap = getBeauty(BITMAP_WIDTH.toInt())
    val bWidth = bitmap.width
    val bHeight = bitmap.height
    val PADDING = 20f.dp
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(RectF(PADDING, PADDING, PADDING + bWidth, PADDING + bHeight), null)
        canvas.drawOval(PADDING, PADDING, PADDING + bWidth, PADDING + bHeight, paint)
        val mode = PorterDuff.Mode.SRC_IN
        paint.xfermode = PorterDuffXfermode(mode)
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)
    }

    private fun getBeauty(width: Int): Bitmap {
        val option = BitmapFactory.Options()
        option.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.beauty, option)
        option.inJustDecodeBounds = false
        option.inDensity = option.outWidth
        option.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.beauty, option)
    }
}

val Float.dp
    get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp
