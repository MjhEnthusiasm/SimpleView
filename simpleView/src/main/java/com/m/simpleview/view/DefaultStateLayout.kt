package com.m.simpleview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.m.simpleview.R

class DefaultStateLayout:FrameLayout {

    private var layoutNoContent:Int = 0
    private var layoutNoNetWord:Int = 0
    private var layoutError:Int = 0
    private var layoutSuccess:Int = 0
    private var layoutLoadding:Int = 0

    private var noContentView: View? = null
    private var noNetWordView: View? = null
    private var errorView: View? = null
    private var successView: View? = null
    private var loadingView: View? = null

    var stateListener:StateListener? = null

    constructor(context: Context, attributeSet: AttributeSet):super(context,attributeSet){
        var typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.DefaultStateLayout)
        layoutNoContent = typedArray.getResourceId(R.styleable.DefaultStateLayout_layout_no_content,0)
        layoutNoNetWord = typedArray.getResourceId(R.styleable.DefaultStateLayout_layout_no_netword,0)
        layoutError = typedArray.getResourceId(R.styleable.DefaultStateLayout_layout_error,0)
        layoutSuccess = typedArray.getResourceId(R.styleable.DefaultStateLayout_layout_success,0)
        layoutLoadding = typedArray.getResourceId(R.styleable.DefaultStateLayout_layout_loadding,0)

    }

    fun showNoNetWord(msg:String?){
        createNoNetWord()
        if(noNetWordView != null){
            val tvNoNetWord = noNetWordView!!.findViewById<TextView>(getId(context,"tv_no_net_word"))
            goneAllChild()
            noNetWordView!!.visibility = View.VISIBLE
            stateListener?.onDefaultStateShow(TYPE_NO_NET_WORD)
            msg?.let { tvNoNetWord?.text = "$msg" }
            if(noNetWordView!!.isClickable){
                noNetWordView?.setOnClickListener {
                    stateListener?.onDefaultStateClick(TYPE_NO_NET_WORD)
                }
            }
        }
    }

    fun goneNoNetWord(){
        if(noNetWordView != null){
            noNetWordView!!.visibility = View.GONE
            stateListener?.onDefaultStateGone(TYPE_NO_NET_WORD)
        }
    }

    fun showNoContent(msg:String?){
        createNoContent()
        if(noContentView != null){
            val tvNoContent = noContentView!!.findViewById<TextView>(getId(context,"tv_no_content"))
            goneAllChild()
            noContentView!!.visibility = View.VISIBLE
            stateListener?.onDefaultStateShow(TYPE_NO_CONTENT)
            msg?.let { tvNoContent?.text = "$msg" }
            if(noContentView!!.isClickable){
                noContentView?.setOnClickListener {
                    stateListener?.onDefaultStateClick(TYPE_NO_CONTENT)
                }
            }
        }
    }

    fun goneNoContent(){
        if(noContentView != null){
            noContentView!!.visibility = View.GONE
            stateListener?.onDefaultStateGone(TYPE_NO_CONTENT)
        }
    }

    fun showError(errorMsg:String?){
        createError()
        if(errorView != null){
            val tvErrorMsg = errorView!!.findViewById<TextView>(getId(context,"tv_error_msg"))
            goneAllChild()
            errorView!!.visibility = View.VISIBLE
            stateListener?.onDefaultStateShow(TYPE_ERROR)
            errorMsg?.let { tvErrorMsg?.text = "$errorMsg" }
            if(errorView!!.isClickable){
                errorView?.setOnClickListener {
                    stateListener?.onDefaultStateClick(TYPE_ERROR)
                }
            }
        }
    }

    fun goneError(){
        if(errorView != null){
            errorView!!.visibility = View.GONE
            stateListener?.onDefaultStateGone(TYPE_ERROR)
        }
    }

    fun showSuccess(successMsg:String?){
        createSuccess()
        if(successView != null){
            val tvSuccessMsg = successView!!.findViewById<TextView>(getId(context,"tv_success_msg"))
            goneAllChild()
            successView!!.visibility = View.VISIBLE
            stateListener?.onDefaultStateShow(TYPE_SUCCESS)
            tvSuccessMsg?.let { tvSuccessMsg?.text = "$successMsg" }
            if(successView!!.isClickable){
                successView?.setOnClickListener {
                    stateListener?.onDefaultStateClick(TYPE_SUCCESS)
                }
            }
        }
    }

    fun goneSuccess(){
        if(successView != null){
            successView!!.visibility = View.GONE
            stateListener?.onDefaultStateGone(TYPE_SUCCESS)
        }
    }

    fun showLoadding(isVisiable:Boolean){
        createLoadding()
        if(loadingView != null){
            if(isVisiable){
                goneAllChild()
                loadingView!!.visibility = View.VISIBLE
                stateListener?.onDefaultStateShow(TYPE_LOADDING)
            }else{
                loadingView!!.visibility = View.GONE
                stateListener?.onDefaultStateGone(TYPE_LOADDING)
            }
        }
    }

    /** set id click **/
    fun <T : View?> setNoNetWordClick(id:Int):T?{
        createNoNetWord()
        if(noNetWordView != null){
            val view = noNetWordView!!.findViewById<T>(id)
            view?.setOnClickListener {
                stateListener?.onDefaultStateClickById(TYPE_NO_NET_WORD,id)
            }
            return view
        }
        return null
    }

    fun <T : View?> setNoContentClick(id:Int):T?{
        createNoContent()
        if(noContentView != null){
            val view = noContentView!!.findViewById<T>(id)
            view?.setOnClickListener {
                stateListener?.onDefaultStateClickById(TYPE_NO_CONTENT,id)
            }
            return view
        }
        return null
    }

    fun <T : View?> setErrorClick(id:Int):T?{
        createError()
        if(errorView != null){
            val view = errorView!!.findViewById<T>(id)
            view?.setOnClickListener {
                stateListener?.onDefaultStateClickById(TYPE_ERROR,id)
            }
            return view
        }
        return null
    }

    fun <T : View?> setSuccessClick(id:Int):T?{
        createSuccess()
        if(successView != null){
            val view = successView!!.findViewById<T>(id)
            view?.setOnClickListener {
                stateListener?.onDefaultStateClickById(TYPE_SUCCESS,id)
            }
            return view
        }
        return null
    }

    fun <T : View?> setLoaddingtClick(id:Int):T?{
        createLoadding()
        if(loadingView != null){
            val view = loadingView!!.findViewById<T>(id)
            view?.setOnClickListener {
                stateListener?.onDefaultStateClickById(TYPE_LOADDING,id)
            }
            return view
        }
        return null
    }

    /** find id **/
    fun <T : View?> findNoNetWordView(id:Int):T?{
        if(noNetWordView != null){
            return noNetWordView!!.findViewById<T>(id)
        }
        return null
    }

    fun <T : View?> findNoContentView(id:Int):T?{
        if(noContentView != null){
            return noContentView!!.findViewById<T>(id)
        }
        return null
    }

    fun <T : View?> findErrorView(id:Int):T?{
        if(errorView != null){
            return errorView!!.findViewById<T>(id)
        }
        return null
    }

    fun <T : View?> findSuccessView(id:Int):T?{
        if(successView != null){
            return successView!!.findViewById<T>(id)
        }
        return null
    }

    fun <T : View?> findLoaddingtView(id:Int):T?{
        if(loadingView != null){
            return loadingView!!.findViewById<T>(id)
        }
        return null
    }

    /** create view **/
    private fun createLoadding(){
        if(loadingView == null && layoutLoadding != 0){
            loadingView = LayoutInflater.from(context).inflate(layoutLoadding,this,false)
            loadingView?.visibility = View.GONE
            addView(loadingView)
        }
    }

    private fun createNoNetWord(){
        if(noNetWordView == null && layoutNoNetWord != 0){
            noNetWordView = LayoutInflater.from(context).inflate(layoutNoNetWord,this,false)
            noNetWordView?.visibility = View.GONE
            addView(noNetWordView)
        }
    }

    private fun createNoContent(){
        if(noContentView == null && layoutNoContent != 0){
            noContentView = LayoutInflater.from(context).inflate(layoutNoContent,this,false)
            noContentView?.visibility = View.GONE
            addView(noContentView)
        }
    }

    private fun createError(){
        if(errorView == null && layoutError != 0){
            errorView = LayoutInflater.from(context).inflate(layoutError,this,false)
            errorView?.visibility = View.GONE
            addView(errorView)
        }
    }

    private fun createSuccess(){
        if(successView == null && layoutSuccess != 0){
            successView = LayoutInflater.from(context).inflate(layoutSuccess,this,false)
            successView?.visibility = View.GONE
            addView(successView)
        }
    }

    private fun getId(context: Context?, name: String?): Int {
        context ?: return 0
        return context.resources.getIdentifier(name, "id", context.packageName)
    }

    fun goneAllChild(){
        for(i in 0 until childCount){
            val view = getChildAt(i)
            if(view.visibility == View.VISIBLE){
                view.visibility = View.GONE
            }
        }
    }

    interface StateListener{
        fun onDefaultStateClickById(type:Int,vid:Int)
        fun onDefaultStateClick(type:Int)
        fun onDefaultStateShow(type:Int)
        fun onDefaultStateGone(type:Int)
    }

    companion object{
        const val TYPE_NO_CONTENT = 0
        const val TYPE_NO_NET_WORD = 1
        const val TYPE_ERROR = 2
        const val TYPE_LOADDING = 3
        const val TYPE_SUCCESS = 4
    }

}