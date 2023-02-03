package com.graduationproject.robokidsapp.model

data class Images(val photo:Int,val name:String, val imageVoice:Int=0) {

    constructor():this(0,"", 0)

}