package com.graduationproject.robokidsapp.ui.kidsFragments.quizzes

import android.content.ClipData
import android.content.ClipDescription
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentEnglishQuiz1Binding
import com.graduationproject.robokidsapp.model.Images



class EnglishQuiz1Fragment : Fragment() {

    private var _binding: FragmentEnglishQuiz1Binding ? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var listImage:ArrayList<Images>
    private lateinit var contentType:String
    companion object{
        lateinit var idCorrect:View
        lateinit var idLayout:View
        var correctCount = 0
    }

    private lateinit var mediaPlayer: MediaPlayer

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

        contentType = arguments?.getString("content_type")!!

        if (contentType == "Arabic"){
            listImage.add(Images(R.drawable.ananas,"اناناس"))
            listImage.add(Images(R.drawable.orange,"برتقال"))
            listImage.add(Images(R.drawable.grape,"عنب"))
            listImage.add(Images(R.drawable.lemon,"ليمون"))
            listImage.add(Images(R.drawable.apple,"تفاح"))
            listImage.add(Images(R.drawable.strawberry,"فروله"))
            listImage.add(Images(R.drawable.banana,"موز"))
            listImage.add(Images(R.drawable.carrot,"خزرة"))
            listImage.add(Images(R.drawable.mango,"مانجو"))
            listImage.add(Images(R.drawable.figs,"تين"))
            listImage.add(Images(R.drawable.peach,"خوخ"))
            listImage.add(Images(R.drawable.tomatoes,"طماطم"))
        }else if (contentType == "English"){
            listImage.add(Images(R.drawable.ananas,"Pineapple"))
            listImage.add(Images(R.drawable.orange,"Orange"))
            listImage.add(Images(R.drawable.grape,"Grape"))
            listImage.add(Images(R.drawable.lemon,"Lemon"))
            listImage.add(Images(R.drawable.apple,"Apple"))
            listImage.add(Images(R.drawable.strawberry,"Strawberry"))
            listImage.add(Images(R.drawable.banana,"Banana"))
            listImage.add(Images(R.drawable.carrot,"Carrot"))
            listImage.add(Images(R.drawable.mango,"Mango"))
            listImage.add(Images(R.drawable.figs,"Figs"))
            listImage.add(Images(R.drawable.peach,"Peach"))
            listImage.add(Images(R.drawable.tomatoes,"Tomatoes"))
        }else{
            listImage.add(Images(R.drawable.count_1,"1"))
            listImage.add(Images(R.drawable.count_2,"2"))
            listImage.add(Images(R.drawable.count_3,"3"))
            listImage.add(Images(R.drawable.count_4,"4"))
            listImage.add(Images(R.drawable.count_5,"5"))
            listImage.add(Images(R.drawable.count_6,"6"))
            listImage.add(Images(R.drawable.count_7,"7"))
            listImage.add(Images(R.drawable.count_8,"8"))
            listImage.add(Images(R.drawable.count_9,"9"))
            listImage.add(Images(R.drawable.count_10,"10"))
            listImage.add(Images(R.drawable.count_11,"11"))
            listImage.add(Images(R.drawable.count_12,"12"))
            binding.tvWord1.textSize = 40f
            binding.tvWord2.textSize = 40f
            binding.tvWord3.textSize = 40f
            binding.tvWord4.textSize = 40f
        }



        listImage.shuffle()
        val list:MutableList<Images> = mutableListOf()
        val listName:MutableList<String> = mutableListOf()

        for (i in 0..3){
            list.add(listImage[i])
            listName.add(listImage[i].name)
        }
        listName.shuffle()
        listName.shuffle()
        binding.img1.setImageResource(list[0].photo)
        binding.img2.setImageResource(list[1].photo)
        binding.img3.setImageResource(list[2].photo)
        binding.img4.setImageResource(list[3].photo)

        binding.tvWord1.text = listName[0]
        binding.tvWord2.text = listName[1]
        binding.tvWord3.text = listName[2]
        binding.tvWord4.text = listName[3]

        binding.layout1.tag = ""+list[0].name
        binding.layout2.tag = ""+list[1].name
        binding.layout3.tag = ""+list[2].name
        binding.layout4.tag = ""+list[3].name

        binding.tvWord1.tag = ""+binding.tvWord1.text
        binding.tvWord2.tag = ""+binding.tvWord2.text
        binding.tvWord3.tag = ""+binding.tvWord3.text
        binding.tvWord4.tag = ""+binding.tvWord4.text

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

        binding.connectQuizBack.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        return binding.root
    }

    fun onLongClick(v: View): Boolean {
        val item = ClipData.Item(""+v.tag)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(""+v.tag, mimeTypes, item)
        val dragShadow = View.DragShadowBuilder(v)
        v.startDrag(data, dragShadow, v, 0)
        return true
    }


    fun onDrag(v: View, event: DragEvent): Boolean {
        val action = event.action

        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                return event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                if(v.background == null){
                    v.background = resources.getDrawable(R.drawable.bg_layout_quiz_connect)
                }

                v.invalidate()
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION ->
                return true
            DragEvent.ACTION_DRAG_EXITED -> {
                if(v.allViews.count() < 3){
                    v.setBackgroundResource(0)
                }
                v.invalidate()
                return true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text.toString()
                if(dragData==v.tag.toString()){
                    v.invalidate()
                    val vw: View = event.localState as View
                    val owner = vw.parent as ViewGroup
                    owner.removeView(vw)
                    val container = v as LinearLayout
                    container.addView(vw)
                    container.background = resources.getDrawable(R.drawable.bg_layout_quiz_connect)
//                    container.setBackgroundResource(R.drawable.bg_layout_quiz_connect)
                    vw.visibility = View.VISIBLE
                    val vwv: TextView = event.localState as TextView
                    vwv.textSize = 20F

                    correctCount++
                    if(correctCount==4){
                        showWinDialog()

                        if(mediaPlayer.isPlaying){
                            mediaPlayer.pause()
                            mediaPlayer.stop()
                            mediaPlayer.seekTo(0)
                        }
                        mediaPlayer = MediaPlayer.create(requireContext() , R.raw.sound_kids)
                        mediaPlayer.start()
                    }

                    mediaPlayer = MediaPlayer.create(requireContext() , R.raw.correct_sound)
                    mediaPlayer.start()

                    return true
                }else{
                    if(v.allViews.count() < 3){
                        mediaPlayer = MediaPlayer.create(requireContext() , R.raw.soundincorrect)
                        mediaPlayer.start()
                        v.setBackgroundResource(0)
                    }
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


    private fun showWinDialog() {
        var flag = false
        val view = layoutInflater.inflate(R.layout.custom_layout_correct_quiz, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(view)
        val alert = dialog.create()

        alert.setOnDismissListener {
            if(!flag) showWinDialog()
        }

        alert.show()

        val button = view.findViewById<Button>(R.id.btn_moreQuestions)

        button.setOnClickListener {
            val action = EnglishQuiz1FragmentDirections.actionEnglishQuiz1FragmentSelf(contentType)
            mNavController.navigate(action)


            alert.dismiss()
            correctCount = 0
            flag = true
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}