package com.graduationproject.robokidsapp.util

import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment


fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.disable(){
    isEnabled = false
}

fun View.enabled(){
    isEnabled = true
}

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
}


//
//fun ChipGroup.addChip(
//    text: String,
//    isTouchTargeSize: Boolean = false,
//    closeIconListener: View.OnClickListener? = null
//) {
//    val chip: Chip = LayoutInflater.from(context).inflate(R.layout.item_chip,null,false) as Chip
//    chip.text = if (text.length > 9) text.substring(0,9) + "..." else text
//    chip.isClickable = false
//    chip.setEnsureMinTouchTargetSize(isTouchTargeSize)
//    if (closeIconListener != null){
//        chip.closeIcon = ContextCompat.getDrawable(context, com.google.android.material.R.drawable.ic_mtrl_chip_close_circle)
//        chip.isCloseIconVisible = true
//        chip.setOnCloseIconClickListener(closeIconListener)
//    }
//    addView(chip)
//}
//
//fun Context.createDialog(layout: Int, cancelable: Boolean): Dialog {
//    val dialog = Dialog(this, android.R.style.Theme_Dialog)
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.setContentView(layout)
//    dialog.window?.setGravity(Gravity.CENTER)
//    dialog.window?.setLayout(
//        WindowManager.LayoutParams.MATCH_PARENT,
//        WindowManager.LayoutParams.WRAP_CONTENT
//    )
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.setCancelable(cancelable)
//    return dialog
//}
//
//val Int.dpToPx: Int
//    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
//
//val Int.pxToDp: Int
//    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
//

fun String.isValidEmail() =
    isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
