package com.graduationproject.robokidsapp.ui.kidsFragments.quizzes

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentMathQuiz1Binding
import com.graduationproject.robokidsapp.model.Images


class MathQuiz1Fragment : Fragment() {
    private var _binding: FragmentMathQuiz1Binding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController
    private lateinit var listImage:ArrayList<Images>
    private lateinit var listOperator:ArrayList<String>
    private lateinit var mediaPlayer: MediaPlayer
    private var counter = 0
    private var numberTrue = 0
    private var numberFalse = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
        listImage = ArrayList()
        listOperator = ArrayList()
        mediaPlayer = MediaPlayer()
    }

    companion object{
        var textNumber = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMathQuiz1Binding.inflate(inflater, container, false)


        binding.btn0.setOnClickListener { changeNumber("0") }
        binding.btn1.setOnClickListener { changeNumber("1") }
        binding.btn2.setOnClickListener { changeNumber("2") }
        binding.btn3.setOnClickListener { changeNumber("3") }
        binding.btn4.setOnClickListener { changeNumber("4") }
        binding.btn5.setOnClickListener { changeNumber("5") }
        binding.btn6.setOnClickListener { changeNumber("6") }
        binding.btn7.setOnClickListener { changeNumber("7") }
        binding.btn8.setOnClickListener { changeNumber("8") }
        binding.btn9.setOnClickListener { changeNumber("9") }
        binding.btnMuns.setOnClickListener { changeNumber("-") }

        binding.delete.setOnClickListener {
            if (textNumber.isNotEmpty()){
                textNumber = textNumber.substring(0, textNumber.length - 1)
                binding.tvMathQuiz1Result.text = textNumber
                if(mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                    mediaPlayer.stop()
                    mediaPlayer.seekTo(0)
                }
                mediaPlayer = MediaPlayer.create(requireContext() , R.raw.click)
                mediaPlayer.start()
            }
        }

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

        listOperator.add("+")
        listOperator.add("+")
        listOperator.add("-")
        listOperator.add("-")
        listOperator.add("+")
        listOperator.add("-")

        listImage.shuffle()
        listOperator.shuffle()

        binding.mathQuizImage1.setImageResource(listImage[counter++].photo)
        binding.mathQuizImage2.setImageResource(listImage[counter++].photo)
        binding.tvOperator.text = listOperator[(counter/2)-1]



        binding.mathQuiz1CheckAnswer.setOnClickListener {
            if (binding.tvMathQuiz1Result.text.isNotEmpty() && binding.tvMathQuiz1Result.text != "-"){
                val answer:Int = binding.tvMathQuiz1Result.text.trim().toString().toInt()
                val operator = binding.tvOperator.text.trim().toString()
                val result:Int
                if (operator == "+"){
                    result = listImage[counter-2].name.toInt() + listImage[counter-1].name.toInt()
                }else{
                    result = listImage[counter-2].name.toInt() - listImage[counter-1].name.toInt()
                }
                if (answer == result){
                    if(mediaPlayer.isPlaying){
                        mediaPlayer.pause()
                        mediaPlayer.stop()
                        mediaPlayer.seekTo(0)
                    }
                    mediaPlayer = MediaPlayer.create(requireContext() , R.raw.soundcorrect)
                    mediaPlayer.start()
                    numberTrue++
                    binding.numberTrue.text = numberTrue.toString()
                    if (counter < 12){
                        binding.tvMathQuiz1Result.text = ""
                        textNumber = ""
                        binding.mathQuizImage1.setImageResource(listImage[counter++].photo)
                        binding.mathQuizImage2.setImageResource(listImage[counter++].photo)
                        binding.tvOperator.text = listOperator[(counter/2)-1]
                    }
                }else{
                    if(mediaPlayer.isPlaying){
                        mediaPlayer.pause()
                        mediaPlayer.stop()
                        mediaPlayer.seekTo(0)
                    }
                    mediaPlayer = MediaPlayer.create(requireContext() , R.raw.soundincorrect)
                    mediaPlayer.start()
                    numberFalse++
                    binding.numberFalse.text = numberFalse.toString()
                    if(numberFalse == 5){
                        showBadDialog()
                    }
                }
                if (counter == listImage.size){
                    showGoodDialog()
                }
            }

        }


        binding.mathQuiz1Exit.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        return binding.root
    }

    private fun changeNumber(num: String) {
        if (textNumber.length < 3) {
            textNumber += num
            binding.tvMathQuiz1Result.text = textNumber
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                mediaPlayer.stop()
                mediaPlayer.seekTo(0)
            }
            mediaPlayer = MediaPlayer.create(requireContext() , R.raw.click)
            mediaPlayer.start()
        }
    }

    private fun showGoodDialog() {
        var flag = false
        val view = layoutInflater.inflate(R.layout.custom_layout_good_result, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(view)
        val alert = dialog.create()

        alert.setOnDismissListener {
            if(!flag) showGoodDialog()
        }

        alert.show()

        val button = view.findViewById<Button>(R.id.btn_moreQuestions)
        var tvTrue = view.findViewById<TextView>(R.id.number_true)
        var tvFalse = view.findViewById<TextView>(R.id.number_false)

        tvTrue.text = numberTrue.toString()
        tvFalse.text = numberFalse.toString()

        button.setOnClickListener {
            binding.tvMathQuiz1Result.text = ""
            textNumber = ""
            val action = MathQuiz1FragmentDirections.actionMathQuiz1FragmentSelf()
            mNavController.navigate(action)
            alert.dismiss()
            flag = true
        }
    }
    private fun showBadDialog() {
        var flag = false
        val view = layoutInflater.inflate(R.layout.custom_layout_bad_result, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(view)
        val alert = dialog.create()

        alert.setOnDismissListener {
            if(!flag) showBadDialog()
        }

        alert.show()

        val button = view.findViewById<Button>(R.id.btn_tryAgain)

        button.setOnClickListener {
            binding.tvMathQuiz1Result.text = ""
            textNumber = ""
            val action = MathQuiz1FragmentDirections.actionMathQuiz1FragmentSelf()
            mNavController.navigate(action)
            alert.dismiss()
            flag = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}