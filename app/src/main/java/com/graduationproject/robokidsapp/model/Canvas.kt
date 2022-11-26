package com.graduationproject.robokidsapp.model

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.graduationproject.robokidsapp.ui.kidsFragments.WhiteboardFragment
import android.graphics.Path
import android.widget.Toast

class Canvas : View {
    companion object{
        val pathList = ArrayList<Path>()
        val colorList = ArrayList<Int>()
        val sizeList = ArrayList<Float>()
        val undoPathList = ArrayList<Path>()
        val undoColorList = ArrayList<Int>()
        val undoSizeList = ArrayList<Float>()
        var currentBrush = Color.BLACK
        var currentSize = WhiteboardFragment.seekBar_size
    }
   lateinit var params: ViewGroup.LayoutParams

   var startFlag = false
   var endFlag = false

    constructor(context: Context?) : super(context){
        init(context!!)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context!!)
    }

    private fun init(context: Context){
        WhiteboardFragment.paint_brush.isAntiAlias = true
        WhiteboardFragment.paint_brush.color = Color.BLACK
        WhiteboardFragment.paint_brush.style = Paint.Style.STROKE
        WhiteboardFragment.paint_brush.strokeCap = Paint.Cap.ROUND
        WhiteboardFragment.paint_brush.strokeJoin = Paint.Join.ROUND
        WhiteboardFragment.paint_brush.strokeWidth = currentSize
        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x
        val y = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                WhiteboardFragment.path.moveTo(x, y)
                startFlag = true
                invalidate()
                true
            }
            MotionEvent.ACTION_MOVE ->{
                WhiteboardFragment.path.lineTo(x, y)
                if(pathList.size==0 || (startFlag && endFlag) ){
                    pathList.add(WhiteboardFragment.path)
                    colorList.add(currentBrush)
                    sizeList.add(currentSize)

                    startFlag = false
                    endFlag = false
                    Toast.makeText(context, ""+ pathList.size, Toast.LENGTH_SHORT).show()
                }
                invalidate()
                true
            }
            MotionEvent.ACTION_UP->{
                WhiteboardFragment.path.moveTo(x, y)
                // this is change Properties of path
                currentBrush = WhiteboardFragment.paint_brush.color
                WhiteboardFragment.path = Path()

                endFlag = true
                invalidate()
            }
            else -> false
        }
        return true
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0 until  pathList.size){
            WhiteboardFragment.paint_brush.color = colorList[i]
            WhiteboardFragment.paint_brush.strokeWidth = sizeList[i]
            canvas!!.drawPath(pathList[i], WhiteboardFragment.paint_brush)
            invalidate()
        }
    }



    fun undo(){
        val size = pathList.size

        if(size > 0){
            undoPathList.add(pathList[size-1])
            undoColorList.add(colorList[size-1])
            undoSizeList.add(sizeList[size-1])
            pathList.removeAt(size-1)
            colorList.removeAt(size-1)
            sizeList.removeAt(size-1)
            invalidate()
        }
    }



    fun redo(){
        val size = undoPathList.size
        if(size > 0){
            pathList.add(undoPathList[size-1])
            colorList.add(undoColorList[size-1])
            sizeList.add(undoSizeList[size-1])
            undoPathList.removeAt(size-1)
            undoColorList.removeAt(size-1)
            undoSizeList.removeAt(size-1)
            invalidate()
        }
    }


}