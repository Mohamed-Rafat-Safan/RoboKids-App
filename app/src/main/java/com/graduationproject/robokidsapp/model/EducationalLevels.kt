package com.graduationproject.robokidsapp.model

class EducationalLevels: EducationalContent{

    var status:Boolean = false
    var levelName:String = ""
    var levelImage:Int = 0

    constructor(status:Boolean,levelName:String,levelImage:Int, contentName: String, contentImage: Int):super(contentName, contentImage){
                    this.status = status
                    this.levelName = levelName
                    this.levelImage = levelImage
                }

    constructor(contentName: String, contentImage: Int):super(contentName, contentImage)

    constructor(status:Boolean,levelName:String,levelImage:Int){
        this.status = status
        this.levelName = levelName
        this.levelImage = levelImage
    }
}
