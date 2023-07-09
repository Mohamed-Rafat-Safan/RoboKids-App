package com.graduationproject.robokidsapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import dagger.hilt.android.internal.Contexts.getApplication
import java.io.IOException
import java.io.InputStream


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}

fun Fragment.toast(msg: String?) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
}


// get avatar from file assets
fun getChildAvatarFormAssets(avatarNum: Int, context: Context): Drawable? {
    val drawableName = "child_avatar/avatar$avatarNum.png"
    var istr: InputStream? = null
    try {
        istr = context.assets.open(drawableName)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return Drawable.createFromStream(istr, null)
}


fun hasInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            return when (type) {
                TYPE_WIFI -> true
                TYPE_MOBILE -> true
                TYPE_ETHERNET -> true
                else -> false
            }
        }
    }

    return false
}