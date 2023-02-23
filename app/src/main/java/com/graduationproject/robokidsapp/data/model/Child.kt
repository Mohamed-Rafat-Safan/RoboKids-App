package com.graduationproject.robokidsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Child(
    var id: String = "",
    val childName: String = "",
    val childImage: Int = 0,
    val gender: String = "",
    val age: Int = 0,
    val createDate:Date = Date()
) : Parcelable
