package com.graduationproject.robokidsapp.ui.kidsFragments.quizzes

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentEnglishQuiz1Binding
import com.graduationproject.robokidsapp.model.Images

class EnglishQuiz1Fragment : Fragment() {

    private var _binding: FragmentEnglishQuiz1Binding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var listImage:ArrayList<Images>
    companion object{
        lateinit var idCorrect:View
        lateinit var idLayout:View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listImage = ArrayList()
        mNavController = findNavController()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEnglishQuiz1Binding.inflate(inflater, container, false)

        listImage.add(Images(R.drawable.ananas,"Pineapple"))
        listImage.add(Images(R.drawable.orange,"Orange"))
        listImage.add(Images(R.drawable.grape,"Grape"))
        listImage.add(Images(R.drawable.lemon,"Lemon"))

        listImage.shuffle()
        val list:MutableList<Images> = mutableListOf()
        val listName:MutableList<String> = mutableListOf()

        for (i in 0..3){
            list.add(listImage[i])
            listName.add(listImage[i].name)
        }
        listName.shuffle()

        binding.img1.setImageResource(list[0].photo)
        binding.img2.setImageResource(list[1].photo)
        binding.img3.setImageResource(list[2].photo)
        binding.img4.setImageResource(list[3].photo)

        binding.tvWord1.text = listName[0]
        binding.tvWord2.text = listName[1]
        binding.tvWord3.text = listName[2]
        binding.tvWord4.text = listName[3]

        binding.layout1.setTag(""+list[0].name)
        binding.layout2.setTag(""+list[1].name)
        binding.layout3.setTag(""+list[2].name)
        binding.layout4.setTag(""+list[3].name)

        binding.tvWord1.setTag(""+binding.tvWord1.text)
        binding.tvWord2.setTag(""+binding.tvWord2.text)
        binding.tvWord3.setTag(""+binding.tvWord3.text)
        binding.tvWord4.setTag(""+binding.tvWord4.text)

        binding.tvWord1.setOnLongClickListener {
            idCorrect = binding.correct1
            onLongClick(it)
        }

        binding.tvWord2.setOnLongClickListener {
            idCorrect = binding.correct2
            onLongClick(it)
        }
        binding.tvWord3.setOnLongClickListener {
            idCorrect = binding.correct3
            onLongClick(it)
        }
        binding.tvWord4.setOnLongClickListener {
            idCorrect = binding.correct4
            onLongClick(it)
        }

        binding.layout1.setOnDragListener { view, dragEvent ->
            idLayout = binding.layout1
            onDrag(view,dragEvent)
        }
        binding.layout2.setOnDragListener { view, dragEvent ->
            idLayout = binding.layout2
            onDrag(view,dragEvent)
        }
        binding.layout3.setOnDragListener { view, dragEvent ->
            idLayout = binding.layout3
            onDrag(view,dragEvent)
        }
        binding.layout4.setOnDragListener { view, dragEvent ->
            idLayout = binding.layout4
            onDrag(view,dragEvent)
        }

        return binding.root
    }

    fun onLongClick(v: View): Boolean {
        val item = ClipData.Item(""+v.getTag())
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(""+v.getTag(), mimeTypes, item)
        val dragshadow = View.DragShadowBuilder(v)
        v.startDrag(data, dragshadow, v, 0)
        return true
    }


    fun onDrag(v: View, event: DragEvent): Boolean {
        val action = event.action
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                return if(event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    true
                } else
                    false
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION ->
                return true
            DragEvent.ACTION_DRAG_EXITED -> {
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text.toString()
                if(dragData==v.getTag().toString()){
                    v.invalidate()
                    val vw: View = event.localState as View
                    val owner = vw.getParent() as ViewGroup
                    owner.removeView(vw)
                    val container = v as LinearLayout
                    container.addView(vw)
                    container.setBackgroundResource(R.drawable.bg_layout_quiz_connect)
                    vw.setVisibility(View.VISIBLE)
                    val vwv: TextView = event.localState as TextView
                    vwv.textSize = 20F
                    return true
                }

            }
            DragEvent.ACTION_DRAG_ENDED -> {
                v.invalidate()
                // Does a getResult(), and displays what happened.
                if (event.result){
                    idCorrect.visibility = View.VISIBLE
                }
                return true
            }
            else -> Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
        }
        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}