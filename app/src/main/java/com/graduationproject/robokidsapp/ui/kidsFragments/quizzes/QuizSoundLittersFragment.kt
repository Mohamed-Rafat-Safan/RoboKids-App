package com.graduationproject.robokidsapp.ui.kidsFragments.quizzes

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentEducationalSectionBinding
import com.graduationproject.robokidsapp.databinding.FragmentQuizSoundLittersBinding
import com.graduationproject.robokidsapp.model.Images
import com.graduationproject.robokidsapp.ui.kidsFragments.EducationalSectionFragmentDirections
import org.intellij.lang.annotations.Language


class QuizSoundLittersFragment : Fragment() {
    private var _binding: FragmentQuizSoundLittersBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNavController: NavController

    private lateinit var listImages: ArrayList<Images>
    private lateinit var listSelectedImages:MutableList<Images>
    private lateinit var targetLetter:Images

    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var language:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuizSoundLittersBinding.inflate(inflater, container, false)

        listImages = ArrayList()

        language = arguments?.getString("language")!!

        if(language=="Arabic"){
            listImages.add(Images(R.drawable.alif, "alif", R.raw.alif))
            listImages.add(Images(R.drawable.baa, "baa", R.raw.baa))
            listImages.add(Images(R.drawable.taa, "taa", R.raw.taa))
            listImages.add(Images(R.drawable.haa, "haa", R.raw.haa))
            listImages.add(Images(R.drawable.raa, "raa", R.raw.raa))
            listImages.add(Images(R.drawable.yaa, "yaa", R.raw.yaa))
        }else{
            listImages.add(Images(R.drawable.char_a, "a", R.raw.char_a))
            listImages.add(Images(R.drawable.char_b, "b", R.raw.char_b))
            listImages.add(Images(R.drawable.char_c, "c", R.raw.char_c))
            listImages.add(Images(R.drawable.char_d, "d", R.raw.char_d))
            listImages.add(Images(R.drawable.char_e, "e", R.raw.char_e))
            listImages.add(Images(R.drawable.char_f, "f", R.raw.char_f))
        }


        getThreeRandomImage()


        binding.letter1.setOnClickListener {
            if(listSelectedImages[0].name == targetLetter.name){
                binding.letter1.setBackgroundResource(R.color.green)
                binding.animationCorrect.visibility = View.VISIBLE
                binding.animationIncorrect.visibility = View.GONE
                binding.letter1.isEnabled = false
                binding.letter2.isEnabled = false
                binding.letter3.isEnabled = false
                binding.animationNext.playAnimation()
                binding.tvNext.isEnabled = true
            }else{
                binding.letter1.setBackgroundResource(R.color.red)
                binding.animationCorrect.visibility = View.GONE
                binding.animationIncorrect.visibility = View.VISIBLE
                binding.letter1.isEnabled = false
            }
        }

        binding.letter2.setOnClickListener {
            if(listSelectedImages[1].name == targetLetter.name){
                binding.letter2.setBackgroundResource(R.color.green)
                binding.animationCorrect.visibility = View.VISIBLE
                binding.animationIncorrect.visibility = View.GONE
                binding.letter1.isEnabled = false
                binding.letter2.isEnabled = false
                binding.letter3.isEnabled = false
                binding.animationNext.playAnimation()
                binding.tvNext.isEnabled = true
            }else{
                binding.letter2.setBackgroundResource(R.color.red)
                binding.animationCorrect.visibility = View.GONE
                binding.animationIncorrect.visibility = View.VISIBLE
                binding.letter2.isEnabled = false
            }
        }

        binding.letter3.setOnClickListener {
            if(listSelectedImages[2].name == targetLetter.name){
                binding.letter3.setBackgroundResource(R.color.green)
                binding.animationCorrect.visibility = View.VISIBLE
                binding.animationIncorrect.visibility = View.GONE
                binding.letter1.isEnabled = false
                binding.letter2.isEnabled = false
                binding.letter3.isEnabled = false
                binding.animationNext.playAnimation()
                binding.tvNext.isEnabled = true
            }else{
                binding.letter3.setBackgroundResource(R.color.red)
                binding.animationCorrect.visibility = View.GONE
                binding.animationIncorrect.visibility = View.VISIBLE
                binding.letter3.isEnabled = false
            }
        }

        binding.ivSound.setOnClickListener {
            binding.animationWaves.playAnimation()

            mediaPlayer = MediaPlayer()
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                mediaPlayer.stop()
                mediaPlayer.seekTo(0)
            }
            mediaPlayer = MediaPlayer.create(requireContext() , targetLetter.imageVoice)
            mediaPlayer.start()

        }


        binding.tvNext.setOnClickListener {
            binding.letter1.isEnabled = true
            binding.letter2.isEnabled = true
            binding.letter3.isEnabled = true
            binding.animationCorrect.visibility = View.GONE
            binding.animationWaves.playAnimation()

            listImages.remove(targetLetter)
            if(listImages.size>=3){
                listSelectedImages.clear()
                getThreeRandomImage()
            }


            if(listImages.size <=2){
                val action =  QuizSoundLittersFragmentDirections.actionQuizSoundLittersFragmentSelf(language)
                mNavController.navigate(action)
            }
        }


        binding.quizSoundLittersExit.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id, true) }
        }

        return binding.root
    }


    fun getThreeRandomImage(){
        listImages.shuffle()
        listSelectedImages = mutableListOf()
        for (i in 0..2){
            listSelectedImages.add(listImages[i])
        }

        targetLetter = listSelectedImages.random()

        mediaPlayer = MediaPlayer.create(requireContext() , targetLetter.imageVoice)
        mediaPlayer.start()

        binding.letter1.setImageResource(listSelectedImages[0].photo)
        binding.letter2.setImageResource(listSelectedImages[1].photo)
        binding.letter3.setImageResource(listSelectedImages[2].photo)

        binding.letter1.setBackgroundResource(0)
        binding.letter2.setBackgroundResource(0)
        binding.letter3.setBackgroundResource(0)

        binding.tvNext.isEnabled = false
        binding.animationNext.pauseAnimation()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}