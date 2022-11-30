package com.graduationproject.robokidsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Child(val childName:String, val childImage:Int):Parcelable {

    constructor() : this("",0)

}