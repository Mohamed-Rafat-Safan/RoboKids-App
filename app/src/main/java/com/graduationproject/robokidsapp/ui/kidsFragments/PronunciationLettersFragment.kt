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
import com.graduationproject.robokidsapp.databinding.FragmentPronunciationLettersBinding
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

        binding.pronunciationLettersExit.setOnClickListener {
            val action = PronunciationLettersFragmentDirections.actionPronunciationLettersFragmentToEducationalSectionFragment("Pronunciation")
            mNavController.navigate(action)
        }

        binding.pronunciationLettersSpeaker.setOnClickListener {
            playRecording()
        }
        binding.pronunciationLettersEnableSpeaker.setOnClickListener {
            playRecording()
        }

        binding.pronunciationLettersMic.setOnClickListener {
            startRecording()
        }
        binding.pronunciationLettersEnableMic.setOnClickListener {
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
            binding.pronunciationLettersSpeaker.visibility = View.INVISIBLE
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
            binding.pronunciationLettersEnableSpeaker.visibility = View.INVISIBLE
            binding.pronunciationLettersSpeaker.visibility = View.VISIBLE
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
                        handler.removeCallbacksAndMessages(null)
                        binding.pronunciationLettersEnableSpeaker.visibility = View.INVISIBLE
                        binding.pronunciationLettersSpeaker.visibility = View.VISIBLE
                        return
                    }
                }
                handler.postDelayed(this,1000)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}