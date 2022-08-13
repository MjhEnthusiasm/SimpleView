package com.m.simpleview.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.m.simpleview.R

class AnimationDrawableView:View {

    private var animationDrawable:Drawable? = null
    private var bgDrawable:Drawable? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        var typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimationDrawableView)
        animationDrawable = typedArray.getDrawable(R.styleable.AnimationDrawableView_animationDrawable)
    }

    fun startAnimation(){
        try {
            if(bgDrawable == null){
                bgDrawable = background
            }

            background = animationDrawable

            val animationDrawable = (background as AnimationDrawable)

            if(!animationDrawable.isRunning){
                animationDrawable.start()
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun stopAnimation(){
        try {
            val animationDrawable = (background as AnimationDrawable)

            if(animationDrawable.isRunning){
                animationDrawable.stop()
            }

            background = bgDrawable
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun isRunning():Boolean{
        try {
            val animationDrawable = (background as AnimationDrawable)
            if(animationDrawable.isRunning){
                return true
            }
        }catch (e:Exception){
        }
        return false
    }

}