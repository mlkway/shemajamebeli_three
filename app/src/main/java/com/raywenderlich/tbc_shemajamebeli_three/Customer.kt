package com.raywenderlich.tbc_shemajamebeli_three

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(val nmae:String, val lastName: String, val email: String):Parcelable{


}
