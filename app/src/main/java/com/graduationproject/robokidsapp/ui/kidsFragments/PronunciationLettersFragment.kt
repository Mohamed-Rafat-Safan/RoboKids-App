package com.graduationproject.robokidsapp.ui.kidsFragments


import android.content.ContextWrapper
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentPronunciationLettersBinding
import com.graduationproject.robokidsapp.data.model.Images
import java.io.File
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class PronunciationLettersFragment : Fragment() {
    private var _binding: FragmentPronunciationLettersBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNavController: NavController

    private var path: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var isRecording: Boolean = false
    private var isPlaying: Boolean = false
    private var second = 0
    private var dummySecond = 0
    private var playableSecond = 0
    lateinit var handler: Handler
    var executor: ExecutorService = Executors.newSingleThreadExecutor()

    private lateinit var contentType:String
    private lateinit var listImages: ArrayList<Images>
    private var imageCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()

        mediaPlayer = MediaPlayer()
        handler = Handler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPronunciationLettersBinding.inflate(inflater, container, false)

        contentType = arguments?.getString("content_type")!!
        listImages = ArrayList()

        if(contentType=="Arabic"){
            listImages.add(Images(R.drawable.alif, "alif", R.raw.alif))
            listImages.add(Images(R.drawable.baa, "baa", R.raw.baa))
            listImages.add(Images(R.drawable.taa, "taa", R.raw.taa))
            listImages.add(Images(R.drawable.haa, "haa", R.raw.haa))
            listImages.add(Images(R.drawable.raa, "raa", R.raw.raa))
            listImages.add(Images(R.drawable.yaa, "yaa", R.raw.yaa))
            binding.letterImage.setImageResource(listImages[imageCounter].photo)
            binding.pronunciationLettersTitle.text = getString(R.string.arabic_letters)
            playSound()
        }else if (contentType=="English"){
            listImages.add(Images(R.drawable.char_a, "a", R.raw.char_a))
            listImages.add(Images(R.drawable.char_b, "b", R.raw.char_b))
            listImages.add(Images(R.drawable.char_c, "c", R.raw.char_c))
            listImages.add(Images(R.drawable.char_d, "d", R.raw.char_d))
            listImages.add(Images(R.drawable.char_e, "e", R.raw.char_e))
            listImages.add(Images(R.drawable.char_f, "f", R.raw.char_f))
            binding.letterImage.setImageResource(listImages[imageCounter].photo)
            binding.pronunciationLettersTitle.text = getString(R.string.english_letters)
            playSound()
        }else if (contentType=="Math"){
            listImages.add(Images(R.drawable.num_1, "1", R.raw.num1))
            listImages.add(Images(R.drawable.num_2, "2", R.raw.num2))
            listImages.add(Images(R.drawable.num_3, "3", R.raw.num3))
            listImages.add(Images(R.drawable.num_4, "4", R.raw.num4))
            listImages.add(Images(R.drawable.num_5, "5", R.raw.num5))
            listImages.add(Images(R.drawable.num_6, "6", R.raw.num6))
            binding.letterImage.setImageResource(listImages[imageCounter].photo)
            binding.pronunciationLettersTitle.text = getString(R.string.numbers)
            playSound()
        }else{
            listImages.add(Images(R.drawable.cat_img, "cat", R.raw.cat))
            listImages.add(Images(R.drawable.dog_img, "dog", R.raw.dog))
            listImages.add(Images(R.drawable.donkey_img, "donkey", R.raw.donkey))
            listImages.add(Images(R.drawable.elephant_img, "elephant", R.raw.elephant))
            listImages.add(Images(R.drawable.lion_img, "lion", R.raw.lion))
            listImages.add(Images(R.drawable.tiger_img, "tiger", R.raw.tiger))
            binding.letterImage.setImageResource(listImages[imageCounter].photo)
            binding.pronunciationLettersTitle.text = getString(R.string.photo)
            playSound()
        }

        binding.pronunciationLettersExit.setOnClickListener {
            val action = PronunciationLettersFragmentDirections.actionPronunciationLettersFragmentToEducationalSectionFragment("Pronunciation")
            mNavController.navigate(action)
        }

        binding.pronunciationLettersEnableSpeaker.setOnClickListener {
            //playRecording()
            binding.pronunciationLettersEnableSpeaker.playAnimation()
            playSound()
        }

        binding.pronunciationLettersMic.setOnClickListener {
            startRecording()
        }
        binding.pronunciationLettersEnableMic.setOnClickListener {
            startRecording()
        }

        binding.pronunciationLettersPrevious.setOnClickListener {
            binding.pronunciationLettersNext.visibility = View.VISIBLE
            binding.pronunciationLettersEnableSpeaker.playAnimation()
            if (imageCounter > 0){
                imageCounter--
                playSound()
                binding.letterImage.setImageResource(listImages[imageCounter].photo)
            }
            if (imageCounter == 0){
                binding.pronunciationLettersPrevious.visibility = View.GONE
            }
        }

        binding.pronunciationLettersNext.setOnClickListener {
            binding.pronunciationLettersPrevious.visibility = View.VISIBLE
            binding.pronunciationLettersEnableSpeaker.playAnimation()
            if (imageCounter < listImages.size-1){
                imageCounter++
                playSound()
                binding.letterImage.setImageResource(listImages[imageCounter].photo)
            }
            if (imageCounter == listImages.size-1){
                binding.pronunciationLettersNext.visibility = View.GONE
            }
        }

        binding.pronunciationLettersExit.setOnClickListener {
            mNavController.currentBackStackEntry?.let { backEntry -> mNavController.popBackStack(backEntry.destination.id,true) }
        }

        return binding.root
    }

    // this method to recording of kids
    fun startRecording() {
        if(!isRecording){
            isRecording = true
            executor.execute {
                mediaRecorder = MediaRecorder()
                mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                mediaRecorder!!.setOutputFile(getRecordingFilePath())
                path = getRecordingFilePath()
                mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

                mediaRecorder!!.prepare()
                mediaRecorder!!.start()

                activity?.runOnUiThread {
                    playableSecond = 0
                    second = 0
                    dummySecond = 0
                    //binding.pronunciationLettersSpeaker.isEnabled = false
                    binding.pronunciationLettersEnableSpeaker.isEnabled = false
                    binding.pronunciationLettersEnableMic.visibility = View.VISIBLE
                    binding.pronunciationLettersMic.visibility = View.INVISIBLE
                    runTimer()
                }
            }
        }else{
            executor.execute {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                mediaRecorder = null
                playableSecond = second
                dummySecond = second
                second = 0
                isRecording = false

                activity?.runOnUiThread{
                    handler.removeCallbacksAndMessages(null)
                    //binding.pronunciationLettersSpeaker.isEnabled = true
                    binding.pronunciationLettersEnableSpeaker.isEnabled = true
                    binding.pronunciationLettersEnableMic.visibility = View.INVISIBLE
                    binding.pronunciationLettersMic.visibility = View.VISIBLE
                }
            }
        }
    } // end method startRecording


    fun playRecording() {
        if (!isPlaying){
            if (path!=null){
                mediaPlayer!!.setDataSource(getRecordingFilePath())
            }else{
                Toast.makeText(requireContext(), "No Recording", Toast.LENGTH_SHORT).show()
                return
            }
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
            isPlaying = true
            //binding.pronunciationLettersSpeaker.isEnabled = true
            binding.pronunciationLettersEnableSpeaker.isEnabled = true
            //binding.pronunciationLettersSpeaker.visibility = View.INVISIBLE
            binding.pronunciationLettersEnableSpeaker.visibility = View.VISIBLE
            runTimer()
        }else{
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
            mediaPlayer = MediaPlayer()
            isPlaying = false
            second = 0
            handler.removeCallbacksAndMessages(null)
            //binding.pronunciationLettersSpeaker.isEnabled = true
            binding.pronunciationLettersEnableSpeaker.isEnabled = true
            binding.pronunciationLettersEnableSpeaker.visibility = View.INVISIBLE
            //binding.pronunciationLettersSpeaker.visibility = View.VISIBLE
        }
    }

    private fun getRecordingFilePath(): String {
        val contextWrapper = ContextWrapper(context)
        val music = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val file = File(music,"testFile"+".mp3")
        return file.path
    }

    private fun runTimer() {
        handler = Handler()
        handler.post(object :Runnable{
            override fun run() {
                val minutes = (second%3600)/60
                val secs = second%60
                var time = String.format(Locale.getDefault(),"%02d:%02d",minutes,secs)
//                textview_sound_recorder_heading.text = time

                if (isRecording || (isPlaying && playableSecond!=-1)){
                    second++
                    playableSecond--
                    if (playableSecond == -1 && isPlaying){
                        mediaPlayer!!.stop()
                        mediaPlayer!!.release()
                        mediaPlayer == null
                        mediaPlayer = MediaPlayer()
                        playableSecond = dummySecond
                        second = 0
                        isPlaying = false
                        handler.removeCallbacksAndMessages(null)
                        binding.pronunciationLettersEnableSpeaker.visibility = View.INVISIBLE
                        //binding.pronunciationLettersSpeaker.visibility = View.VISIBLE
                        return
                    }
                }
                handler.postDelayed(this,1000)
            }

        })
    }

    fun playSound(){
        if(mediaPlayer?.isPlaying!!){
            mediaPlayer?.pause()
            mediaPlayer?.stop()
            mediaPlayer?.seekTo(0)
        }
        mediaPlayer = MediaPlayer.create(requireContext() , listImages[imageCounter].imageVoice)
        mediaPlayer?.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}