package com.graduationproject.robokidsapp.ui.kidsFragments

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.*
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.adapters.TabWidgetBindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.WhiteboardAdapter
import com.graduationproject.robokidsapp.databinding.FragmentEducationalContentBinding
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import com.graduationproject.robokidsapp.databinding.FragmentWhiteboardBinding
import com.graduationproject.robokidsapp.model.Canvas
import com.graduationproject.robokidsapp.model.Content


class WhiteboardFragment : Fragment() {
    private lateinit var mNavController: NavController
    lateinit var listLetters:ArrayList<String>
    lateinit var typeLetter:String

    companion object{
        var path:Path = Path()
        var paint_brush = Paint()

        private var _binding: FragmentWhiteboardBinding? = null
        val binding get() = _binding!!

        var seekBar_size = 10f
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNavController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWhiteboardBinding.inflate(inflater, container, false)


        binding.customToolbar.inflateMenu(R.menu.whiteboard_menu)

        binding.customToolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.pencil -> {
                    currentColor(paint_brush.color)
                    true
                }
                R.id.eraser -> {
                    Canvas.pathList.clear()
                    Canvas.colorList.clear()
                    Canvas.sizeList.clear()
                    path.reset()
                    true
                }
                R.id.undo ->{
                    binding.mCanvas.undo()
                    true
                }
                R.id.redo ->{
                  binding.mCanvas.redo()
                    true
                }
                R.id.black -> {
                    paint_brush.color = Color.BLACK
                    currentColor(paint_brush.color)
                    true
                }
                R.id.red -> {
                    paint_brush.color = Color.RED
                    currentColor(paint_brush.color)
                    true
                }
                R.id.green -> {
                    paint_brush.color = Color.GREEN
                    currentColor(paint_brush.color)
                    true
                }
                R.id.purple -> {
                    paint_brush.color = Color.MAGENTA
                    currentColor(paint_brush.color)
                    true
                }
                R.id.yellow -> {
                    paint_brush.color = Color.YELLOW
                    currentColor(paint_brush.color)
                    true
                }
                R.id.blue -> {
                    paint_brush.color = Color.BLUE
                    currentColor(paint_brush.color)
                    true
                }
                else -> false
            }
        }


        typeLetter = arguments?.getString("learnSection")!!
        // this fill arrayList by type letters
        setDataInArrayList(typeLetter)


        val adapter = WhiteboardAdapter(requireContext() , listLetters)
        binding.rvWhiteboard.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvWhiteboard.adapter = adapter
        binding.rvWhiteboard.setHasFixedSize(true)


        binding.seekBarSize.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                seekBar_size = p1.toFloat()
                 currentSize(seekBar_size)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


        binding.whiteboardBack.setOnClickListener {
            val action = WhiteboardFragmentDirections.actionWhiteboardFragmentToEducationalSectionFragment("Board")
            mNavController.navigate(action)
        }


        return binding.root
    }


    fun currentColor(c:Int){
        Canvas.currentBrush = c
        path = Path()
    }

    fun currentSize(size:Float){
        Canvas.currentSize = size
        path = Path()
    }



    fun setDataInArrayList(type:String){
        listLetters = ArrayList()
        when(type){
            "Arabic"->{
                for(i in 1569..1594){
                    listLetters.add(""+ i.toChar())
                }
                for(i in 1601..1610){
                    listLetters.add(""+ i.toChar())
                }
            }
            "English"->{
                for(i in 'A'..'Z'){
                    listLetters.add(""+ i)
                }
            }
            "Math"->{
                for(i in 0..100){
                    listLetters.add(""+ i)
                }
            }
            else -> println("invalid type")
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}