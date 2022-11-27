package com.graduationproject.robokidsapp.ui.kidsFragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.databinding.FragmentContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentIntertainmentVidoFramBinding

class IntertainmentVidoFramFragment : Fragment() {

    private var _binding: FragmentIntertainmentVidoFramBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntertainmentVidoFramBinding.inflate(inflater, container, false)

        val videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

        // Uri object to refer the
        // resource from the videoUrl
        val uri = Uri.parse(videoUrl)
        // sets the resource from the
        // videoUrl to the videoView
        binding.vvVideoContent.setVideoURI(uri)
//        binding.vvVideoContent.setVideoPath("android.resource://"+activity?.packageName+"/"+R.raw.mo)
        // creating object of
        // media controller class
        val mediaController = MediaController(requireContext())
        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(binding.vvVideoContent)
        // sets the media player to the videoView
        mediaController.setMediaPlayer(binding.vvVideoContent)
        // sets the media controller to the videoView
        binding.vvVideoContent.setMediaController(mediaController)
        // starts the video
        binding.vvVideoContent.start()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}