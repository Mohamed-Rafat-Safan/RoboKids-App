package com.graduationproject.robokidsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class EducationalContent(var contentName:String, var contentImage:Int):Parcelable {

    constructor() : this("",0)

}