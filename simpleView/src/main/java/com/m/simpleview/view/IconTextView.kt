package com.m.simpleview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.m.simpleview.R
import kotlin.math.roundToInt

open class IconTextView :AppCompatTextView {

    private var isFirstSetDrawable = false

    var drawableOne: Drawable? = null
    var drawableTwo: Drawable? = null

    private var drawableOneGravity = 0
    private var drawableOneLeft = 0
    private var drawableOneRight = 0
    private var drawableOneTop = 0
    private var drawableOneBottom = 0
    private var drawableOneWidth = 0
    private var drawableOneHeight = 0

    private var drawableTwoGravity = 0
    private var drawableTwoLeft = 0
    private var drawableTwoRight = 0
    private var drawableTwoTop = 0
    private var drawableTwoBottom = 0
    private var drawableTwoWidth = 0
    private var drawableTwoHeight = 0

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            RenderNode(javaClass.name, ViewAnimationHostBridge(this))
//        }
        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconTextView)

        drawableOne = typedArray.getDrawable(R.styleable.IconTextView_drawableOne)
        drawableOneGravity = typedArray.getInt(R.styleable.IconTextView_drawableOneGravity,0)
        drawableOneWidth = typedArray.getDimension(R.styleable.IconTextView_drawableOneWidth,0f).roundToInt()
        drawableOneHeight = typedArray.getDimension(R.styleable.IconTextView_drawableOneHeight,0f).roundToInt()
        drawableOneLeft = typedArray.getDimension(R.styleable.IconTextView_drawableOneLeft,0f).roundToInt()
        drawableOneRight = typedArray.getDimension(R.styleable.IconTextView_drawableOneRight,0f).roundToInt()
        drawableOneTop = typedArray.getDimension(R.styleable.IconTextView_drawableOneTop,0f).roundToInt()
        drawableOneBottom = typedArray.getDimension(R.styleable.IconTextView_drawableOneBottom,0f).roundToInt()

        drawableTwo = typedArray.getDrawable(R.styleable.IconTextView_drawableTwo)
        drawableTwoGravity = typedArray.getInt(R.styleable.IconTextView_drawableTwoGravity,0)
        drawableTwoWidth = typedArray.getDimension(R.styleable.IconTextView_drawableTwoWidth,0f).roundToInt()
        drawableTwoHeight = typedArray.getDimension(R.styleable.IconTextView_drawableTwoHeight,0f).roundToInt()
        drawableTwoLeft = typedArray.getDimension(R.styleable.IconTextView_drawableTwoLeft,0f).roundToInt()
        drawableTwoRight = typedArray.getDimension(R.styleable.IconTextView_drawableTwoRight,0f).roundToInt()
        drawableTwoTop = typedArray.getDimension(R.styleable.IconTextView_drawableTwoTop,0f).roundToInt()
        drawableTwoBottom = typedArray.getDimension(R.styleable.IconTextView_drawableTwoBottom,0f).roundToInt()

        drawableOne()
        drawableTwo()

        Log.i("margin","constructor")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(isFirstSetDrawable){
            isFirstSetDrawable = false
            configureBounds()
        }

        drawableOne?.let {
            it.draw(canvas!!)
        }

        drawableTwo?.let {
            it.draw(canvas!!)
        }
    }

    @JvmName("setDrawableOne1")
    fun setDrawableOne(drawable: Drawable){
        this.drawableOne = drawable
        drawableOne()
        invalidate()
    }

    @JvmName("setDrawableTwo1")
    fun setDrawableTwo(drawable: Drawable){
        this.drawableTwo = drawable
        drawableTwo()
        invalidate()
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        drawableOne?.let {
            val drawable: Drawable = it
            if (drawable != null
                && drawable.isStateful
                && drawable.setState(drawableState)
            ) {
                configureDrawableOneBounds()
            }
        }
        drawableTwo?.let {
            val drawable: Drawable = it
            if (drawable != null
                && drawable.isStateful
                && drawable.setState(drawableState)
            ) {
                configureDrawableTwoBounds()
            }
        }
    }

    override fun invalidateDrawable(drawable: Drawable) {
        super.invalidateDrawable(drawable)
        invalidateDrawable()
    }

    private fun drawableOne(){
        drawableOne?.let {
            it.setCallback(this)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.setLayoutDirection(layoutDirection)
            }
            if (it.isStateful()) {
                it.setState(drawableState)
            }
            isFirstSetDrawable = true
        }
    }

    private fun drawableTwo(){
        drawableTwo?.let {
            it.setCallback(this)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.setLayoutDirection(layoutDirection)
            }
            if (it.isStateful()) {
                it.setState(drawableState)
            }
            isFirstSetDrawable = true
        }
    }

    private fun invalidateDrawable(){
        configureBounds()
        invalidate()
    }

    private fun configureBounds() {
        configureDrawableOneBounds()
        configureDrawableTwoBounds()
    }

    private fun configureDrawableOneBounds(){
        if (drawableOne == null) {
            return
        }
        val tmpDrawableRight = drawableOneRight
        val tmpDrawableBottom = drawableOneBottom
        val temdrawableLeft = if(
            drawableOneGravity == Gravity.CENTER_HORIZONTAL
            || drawableOneGravity == Gravity.CENTER
            || drawableOneGravity == Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            || drawableOneGravity == Gravity.TOP or Gravity.CENTER_HORIZONTAL){
            (measuredWidth/2)-(drawableOneWidth/2)

        }else if(drawableOneGravity == Gravity.RIGHT
            || drawableOneGravity == Gravity.RIGHT or Gravity.CENTER_VERTICAL
            || drawableOneGravity == Gravity.BOTTOM or Gravity.RIGHT){
            if(tmpDrawableRight != 0){
                measuredWidth - drawableOneWidth - tmpDrawableRight
            }else{
                measuredWidth - drawableOneWidth
            }

        }else{
            drawableOneLeft
        }
        val temdrawableTop = if(
            drawableOneGravity == Gravity.CENTER_VERTICAL
            || drawableOneGravity == Gravity.CENTER){
            (measuredHeight/2)-(drawableOneHeight/2)

        }else if(drawableOneGravity == Gravity.RIGHT or Gravity.CENTER_VERTICAL){
            (measuredHeight/2)-(drawableOneHeight/2)

        }else if(drawableOneGravity == Gravity.BOTTOM
            || drawableOneGravity == Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            || drawableOneGravity == Gravity.BOTTOM or Gravity.RIGHT){
            if(tmpDrawableBottom != 0){
                measuredHeight - drawableOneHeight - tmpDrawableBottom
            }else{
                measuredHeight - drawableOneHeight
            }

        }else{
            drawableOneTop
        }
        drawableOne?.setBounds(temdrawableLeft, temdrawableTop, drawableOneWidth+temdrawableLeft, drawableOneHeight+temdrawableTop)
    }

    private fun configureDrawableTwoBounds(){
        if (drawableTwo == null) {
            return
        }
        val tmpDrawableRight = drawableTwoRight
        val tmpDrawableBottom = drawableTwoBottom
        val temdrawableLeft = if(
            drawableTwoGravity == Gravity.CENTER_HORIZONTAL
            || drawableTwoGravity == Gravity.CENTER
            || drawableTwoGravity == Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            || drawableTwoGravity == Gravity.TOP or Gravity.CENTER_HORIZONTAL){
            (measuredWidth/2)-(drawableTwoWidth/2)

        }else if(drawableTwoGravity == Gravity.RIGHT
            || drawableTwoGravity == Gravity.RIGHT or Gravity.CENTER_VERTICAL
            || drawableTwoGravity == Gravity.BOTTOM or Gravity.RIGHT){
            if(tmpDrawableRight != 0){
                measuredWidth - drawableTwoWidth - tmpDrawableRight
            }else{
                measuredWidth - drawableTwoWidth
            }

        }else{
            drawableTwoLeft
        }
        val temdrawableTop = if(
            drawableTwoGravity == Gravity.CENTER_VERTICAL
            || drawableTwoGravity == Gravity.CENTER){
            (measuredHeight/2)-(drawableTwoHeight/2)

        }else if(drawableTwoGravity == Gravity.RIGHT or Gravity.CENTER_VERTICAL){
            (measuredHeight/2)-(drawableTwoHeight/2)

        }else if(drawableTwoGravity == Gravity.BOTTOM
            || drawableTwoGravity == Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            || drawableTwoGravity == Gravity.BOTTOM or Gravity.RIGHT){
            if(tmpDrawableBottom != 0){
                measuredHeight - drawableTwoHeight - tmpDrawableBottom
            }else{
                measuredHeight - drawableTwoHeight
            }

        }else{
            drawableTwoTop
        }
        drawableTwo?.setBounds(temdrawableLeft, temdrawableTop, drawableTwoWidth+temdrawableLeft, drawableTwoHeight+temdrawableTop)
    }


}