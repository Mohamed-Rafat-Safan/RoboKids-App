package com.graduationproject.robokidsapp.ui.kidsFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.databinding.FragmentPronunciationLettersBinding


class PronunciationLettersFragment : Fragment() {
    private var _binding: FragmentPronunciationLettersBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPronunciationLettersBinding.inflate(inflater, container, false)


        binding.pronunciationLettersExit.setOnClickListener {
            val action = PronunciationLettersFragmentDirections.actionPronunciationLettersFragmentToEducationalSectionFragment("Pronunciation")
            mNavController.navigate(action)
        }

        binding.pronunciationLettersSpeaker.setOnClickListener {
            binding.pronunciationLettersSpeaker.visibility = View.INVISIBLE
            binding.pronunciationLettersEnableSpeaker.visibility = View.VISIBLE
            binding.pronunciationLettersEnableMic.visibility = View.INVISIBLE
            binding.pronunciationLettersMic.visibility = View.VISIBLE

            playRecording()
        }

        binding.pronunciationLettersMic.setOnClickListener {
            binding.pronunciationLettersEnableMic.visibility = View.VISIBLE
            binding.pronunciationLettersMic.visibility = View.INVISIBLE
            binding.pronunciationLettersEnableSpeaker.visibility = View.INVISIBLE
            binding.pronunciationLettersSpeaker.visibility = View.VISIBLE


            startRecording()

        }

        binding.pronunciationLettersPrevious.setOnClickListener {

        }

        binding.pronunciationLettersNext.setOnClickListener {

        }

        return binding.root
    }


    // this method to recording of kids
    fun startRecording() {

    } // end method startRecording


    fun playRecording() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}