package com.graduationproject.robokidsapp.adapters

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.model.Canvas
import com.graduationproject.robokidsapp.model.WhiteBoardContent
import com.graduationproject.robokidsapp.ui.kidsFragments.WhiteboardFragment
import java.lang.String.format
import java.util.Locale


class WhiteboardAdapter(val context: Context , val listData:ArrayList<WhiteBoardContent>,val isImage:Boolean):Adapter<WhiteboardAdapter.WhiteboardViewHolder>() {

    private val START_TIME_IN_MILLIS: Long = 60000
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS
    val mTextViewCountDown = WhiteboardFragment.binding.timer
    var isRunning:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiteboardViewHolder {
        if(isImage){
            return WhiteboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_image_know , parent,false))
        }else{
            return WhiteboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_letters , parent,false))
        }

    }
    override fun onBindViewHolder(holder: WhiteboardViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            if(isImage){
                WhiteboardFragment.binding.whiteboardImageContent.setImageResource(data.image)
                WhiteboardFragment.binding.tvNameImage.text = data.imageName
            }else{
                WhiteboardFragment.binding.tvText.text = data.letter
            }
            WhiteboardFragment.binding.animationCorrect.visibility = View.GONE
            Canvas.pathList.clear()
            Canvas.colorList.clear()
            Canvas.sizeList.clear()
            WhiteboardFragment.path.reset()

            mTextViewCountDown.setTextColor(Color.parseColor("#009688"))
            mCountDownTimer?.cancel()
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            updateCountDownText()
            itemTimer()
        }
        WhiteboardFragment.binding.whiteboardCheckAnswer.setOnClickListener {
            mCountDownTimer?.cancel()
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            updateCountDownText()
            WhiteboardFragment.binding.animationCorrect.visibility = View.VISIBLE
//            val bmp = WhiteboardFragment.binding.mCanvas.getBitmap()
//            WhiteboardFragment.binding.whiteboardImageContent.setImageBitmap(bmp)
        }
    }

    private fun itemTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                val num = (mTimeLeftInMillis / 1000).toInt() % 60
                if(num == 10){
                    mTextViewCountDown.setTextColor(Color.RED)
                }
                updateCountDownText()
            }

            override fun onFinish() {
//                WhiteboardFragment().savePhoto()
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted: String = format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        mTextViewCountDown.text = timeLeftFormatted
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    inner class WhiteboardViewHolder(itemView: View) : ViewHolder(itemView){
        fun bind(content: WhiteBoardContent){
            if (isImage){
                val image = itemView.findViewById<ImageView>(R.id.iv_imgKnow)
                image.setImageResource(content.image)
            }else{
                val textView = itemView.findViewById<TextView>(R.id.tv_letter)
                textView.text = content.letter
            }

        }
    }
}