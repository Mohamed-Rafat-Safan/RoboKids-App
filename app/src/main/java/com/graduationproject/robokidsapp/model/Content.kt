package com.graduationproject.robokidsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Content(var contentName:String, var contentImage:Int): Parcelable {
    constructor() : this("",0)
}