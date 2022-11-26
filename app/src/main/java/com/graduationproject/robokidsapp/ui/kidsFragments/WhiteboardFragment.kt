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
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduationproject.robokidsapp.R
import com.graduationproject.robokidsapp.adapters.WhiteboardAdapter
import com.graduationproject.robokidsapp.databinding.FragmentRegisterBinding
import com.graduationproject.robokidsapp.databinding.FragmentWhiteboardBinding
import com.graduationproject.robokidsapp.model.Canvas


class WhiteboardFragment : Fragment() {

    companion object{
        var path:Path = Path()
        var paint_brush = Paint()


        private var _binding: FragmentWhiteboardBinding? = null
        val binding get() = _binding!!

        var seekBar_size = 10f
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        val data = ArrayList<String>()
        for (i in 'A'..'Z'){
            data.add(""+i)
        }

        val adapter = WhiteboardAdapter(requireContext() , data)
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



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}